package `in`.mockapi.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import `in`.mockapi.dto.AddNewRequestDTO
import `in`.mockapi.dto.AddNewResponseConditionRequestDTO
import `in`.mockapi.dto.ApiResponseBodyDTO
import `in`.mockapi.factory.CryptoFactory
import `in`.mockapi.model.ConditionCombinationEntity
import `in`.mockapi.model.ConditionEntity
import `in`.mockapi.model.ConfigurationEntity
import `in`.mockapi.repository.ConditionCombinationRepository
import `in`.mockapi.repository.ConditionRepository
import `in`.mockapi.repository.ConfigurationRepository
import `in`.mockapi.service.encryption.CryptoService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class DummyApiService(
    private val configurationRepository: ConfigurationRepository,
    private val conditionRepository: ConditionRepository,
    private val conditionCombinationRepository: ConditionCombinationRepository,
    private val cryptoFactory: CryptoFactory,
    private val dummyApiLogicService: DummyApiLogicService,
) {

    fun addNewDummyApiName(requestPayload: AddNewRequestDTO): String {

        val config = configurationRepository.findByName(requestPayload.name)

        if (config == null) {

            configurationRepository.save(
                createConfigEntity(requestPayload)
            )

            return createSuccessResponseEntity(
                message = "Successfully created dummy api name."
            )

        } else return createFailureResponseEntity("Failed to create dummy api. Api name already exists.")
    }

    fun addNewResponseCondition(
        pathName: String,
        requestPayload: AddNewResponseConditionRequestDTO
    ): String {

        val configuration =
            configurationRepository.findByName(pathName) ?: return "Failed. Configuration name not found."

        val conditionCombinations = mutableListOf<Long>()

        requestPayload.condition.forEach {

            val conditionAlreadySaved = conditionRepository.findByConditionKeyAndConditionValue(it.key, it.value)

            if (conditionAlreadySaved == null) {
                val condition: ConditionEntity = ConditionEntity().apply {
                    configurationEntity = configuration
                    conditionKey = it.key
                    conditionValue = it.value
                }

                val savedConditionEntity = conditionRepository.save(condition)

                conditionCombinations.add(savedConditionEntity.id)

            } else conditionCombinations.add(conditionAlreadySaved.id)
        }

        val conditionCombinationEntity: ConditionCombinationEntity = ConditionCombinationEntity().apply {
            conditionCombination = conditionCombinations
            response = jacksonObjectMapper().writeValueAsString(requestPayload.responseToBeSaved)
            configurationEntity = configuration
        }

        conditionCombinationRepository.save(conditionCombinationEntity)

        return createSuccessResponseEntity("Successfully added new response for api name")
    }

    @Transactional
    fun deleteApiNameAndAllConditions(name: String): String {

        val configuration =
            configurationRepository.findByName(name) ?: throw Exception("Failed. Configuration name not found.")

        conditionCombinationRepository.deleteByConfigurationEntity(configuration)

        conditionRepository.deleteAllByConfigurationEntity(configuration)

        configurationRepository.delete(configuration)

        return createSuccessResponseEntity("Successfully deleted api name and all related conditions")
    }

    fun listAllForName(name: String): String {

        val configuration =
            configurationRepository.findByName(name) ?: throw Exception("Failed. Configuration name not found.")

        return createSuccessResponseEntity(
            jacksonObjectMapper().writeValueAsString(dummyApiLogicService.getList(configuration,name))
        )
    }

    fun listOfAllNames(): String {

        val configurationNames = configurationRepository.findAllNames()

        return createSuccessResponseEntity(
            jacksonObjectMapper().writeValueAsString(configurationNames)
        )
    }

    fun getResponse(name: String, requestPayload: Map<String, Any>): String {

        val configuration = configurationRepository.findByName(name)
            ?: return "Failed. Configuration name not found."


        if (configuration.isEncryptionEnabled) {

            if (configuration.cryptoService.isNullOrBlank()) throw Exception("Crypto service not found")
            else {

                val cryptoService = getEncryptionService(configuration.cryptoService!!)

                return getResponseString(configuration, cryptoService, requestPayload.toMutableMap())

            }
        } else {
            return dummyApiLogicService.getResponse(configuration, requestPayload)
        }
    }

    private fun createConfigEntity(requestPayload: AddNewRequestDTO): ConfigurationEntity {

        return ConfigurationEntity().apply {
            name = requestPayload.name
            requestJson = jacksonObjectMapper().writeValueAsString(requestPayload.request)
            responseJson = jacksonObjectMapper().writeValueAsString(requestPayload.response)
            isEncryptionEnabled = requestPayload.isEncrypted
            cryptoService = requestPayload.encryptionService
            encryptedRequestKey = requestPayload.encryptedRequestKey
            encryptedResponseKey = requestPayload.encryptedResponseKey
        }

    }

    private fun createSuccessResponseEntity(message: String): String {
        return jacksonObjectMapper().writeValueAsString(
            ApiResponseBodyDTO(
                statusCode = 200,
                status = "Success",
                message = message
            )
        )
    }

    private fun createFailureResponseEntity(message: String): String {
        return jacksonObjectMapper().writeValueAsString(
            ApiResponseBodyDTO(
                statusCode = 400,
                status = "Failed",
                message = message
            )
        )
    }

    private fun getResponseString(
        configuration: ConfigurationEntity,
        cryptoService: CryptoService,
        requestPayload: MutableMap<String, Any>
    ): String {

        var requestMap = requestPayload

        if (!configuration.encryptedRequestKey.isNullOrBlank()) {

            val encryptedValue: String =
                parseAndReturnStringValueInMap(configuration.encryptedRequestKey!!, requestPayload)
                    ?: throw Exception("encryptedRequestKey not found in request body")

            val decryptedRequestValue =
                getDecryptedData(cryptoService, encryptedValue)

            requestMap = parseAndReplaceValueInMapForRequest(
                configuration.encryptedRequestKey!!,
                requestPayload,
                decryptedRequestValue
            )

        }

        val responseString = dummyApiLogicService.getResponse(configuration, requestMap)

        if (!configuration.encryptedResponseKey.isNullOrBlank()) {

            val responseMap =
                jacksonObjectMapper().readValue(
                    responseString,
                    object : TypeReference<MutableMap<String, Any>>() {})

            val valueToEncrypt: String =
                parseAndReturnJsonValueAsStringInMap(configuration.encryptedResponseKey!!, responseMap)
                    ?: throw Exception("encryptedRequestKey not found in request body")

            val encryptedResponseData: String =
                getEncryptedData(cryptoService, valueToEncrypt)

            val responseMapWithEncryptedData = parseAndReplaceValueInMapForResponse(
                configuration.encryptedResponseKey!!,
                responseMap,
                encryptedResponseData
            )

            return jacksonObjectMapper().writeValueAsString(responseMapWithEncryptedData)

        } else return responseString
    }

    private fun getDecryptedData(cryptoService: CryptoService, data: String): String {
        return cryptoService.getDecryptedData(
            encryptedData = data,
            certificateFilePath = null
        )
    }

    private fun getEncryptedData(cryptoService: CryptoService, data: Any?): String {
        return cryptoService.getEncryptedData(
            data = jacksonObjectMapper()
                .writeValueAsString(
                    data
                ),
            certificateFilePath = null
        )
    }

    private fun getEncryptionService(name: String): CryptoService {
        return cryptoFactory.getCryptoService(name)
    }

    // Used because of inner json
    private fun parseAndReturnStringValueInMap(
        mapKey: String,
        requestPayload: Map<String, Any>
    ): String? {

        var returnValue: String? = ""

        requestPayload.forEach { (key, value) ->

            val parsedValue = jacksonObjectMapper().writeValueAsString(value)

            if (isJsonValid(parsedValue)) {

                val innerJsonValue =
                    jacksonObjectMapper().readValue(parsedValue, object : TypeReference<Map<String, Any>>() {})

                returnValue = parseAndReturnStringValueInMap(mapKey, innerJsonValue)

            }

            if (key == mapKey) {

                returnValue = value.toString()

            }
        }

        return returnValue
    }

    // Used because of inner json
    private fun parseAndReplaceValueInMapForRequest(
        mapKey: String,
        payloadMap: MutableMap<String, Any>,
        valueToReplace: String
    ): MutableMap<String, Any> {

        var returnMap: MutableMap<String, Any> = mutableMapOf()

        payloadMap.forEach { (key, value) ->

            val parsedValue = jacksonObjectMapper().writeValueAsString(value)

            if (isJsonValid(parsedValue)) {

                val innerJsonValue: MutableMap<String, Any> =
                    jacksonObjectMapper().readValue(
                        parsedValue,
                        object : TypeReference<MutableMap<String, Any>>() {})

                returnMap = parseAndReplaceValueInMapForRequest(mapKey, innerJsonValue, valueToReplace)

            }

            returnMap[key] = value

            if (key == mapKey) {

                returnMap[key] = valueToReplace

                return returnMap

            }

        }

        return returnMap
    }

    // Used because of inner json
    private fun parseAndReplaceValueInMapForResponse(
        mapKey: String,
        payloadMap: MutableMap<String, Any>,
        valueToReplace: String
    ): MutableMap<String, Any> {

        payloadMap.forEach { (key, value) ->

            val parsedValue = jacksonObjectMapper().writeValueAsString(value)

            if (isJsonValid(parsedValue)) {

                val innerJsonValue: MutableMap<String, Any> =
                    jacksonObjectMapper().readValue(
                        parsedValue,
                        object : TypeReference<MutableMap<String, Any>>() {}
                    )

                payloadMap[key] = parseAndReplaceValueInMapForResponse(mapKey, innerJsonValue, valueToReplace)

            }

            if (key == mapKey) {

                payloadMap[key] = valueToReplace

            }

        }

        return payloadMap
    }

    // Used because of inner json
    private fun parseAndReturnJsonValueAsStringInMap(
        mapKey: String,
        requestPayload: Map<String, Any>
    ): String? {

        var returnValue: String? = ""

        requestPayload.forEach { (key, value) ->

            val parsedValue = jacksonObjectMapper().writeValueAsString(value)

            if (isJsonValid(parsedValue)) {

                val innerJsonValue =
                    jacksonObjectMapper().readValue(parsedValue, object : TypeReference<Map<String, Any>>() {})

                returnValue = parseAndReturnStringValueInMap(mapKey, innerJsonValue)

            }

            if (key == mapKey) {

                returnValue = jacksonObjectMapper().writeValueAsString(value)

            }
        }

        return returnValue
    }

    private fun isJsonValid(jsonInString: String): Boolean {
        try {

            jacksonObjectMapper().readValue(jsonInString, object : TypeReference<Map<String, Any>>() {})

            return true

        } catch (ex: Exception) {

            return false

        }
    }

}