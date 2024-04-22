package `in`.mockapi.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import `in`.mockapi.dto.AddNewRequestDTO
import `in`.mockapi.dto.AddNewResponseConditionRequestDTO
import `in`.mockapi.model.ConditionCombinationEntity
import `in`.mockapi.model.ConditionEntity
import `in`.mockapi.model.ConfigurationEntity
import `in`.mockapi.repository.ConditionCombinationRepository
import `in`.mockapi.repository.ConditionRepository
import `in`.mockapi.repository.ConfigurationRepository
import `in`.mockapi.service.bank.AxisBankServiceImpl
import org.springframework.stereotype.Service


@Service
class DummyBankService(
    private val configurationRepository: ConfigurationRepository,
    private val conditionRepository: ConditionRepository,
    private val conditionCombinationRepository: ConditionCombinationRepository,
    private val axisBankServiceImpl: AxisBankServiceImpl
) {

    fun getEncryptedRequest(
        bankName: String,
        requestPayload: String
    ): String {

        return axisBankServiceImpl.getEncryptedRequest(bankName,requestPayload)
    }
    fun addNewDummyApiName(requestPayload: AddNewRequestDTO): String {

        val configuration: ConfigurationEntity = ConfigurationEntity().apply {
            name = requestPayload.name
            requestJson = requestPayload.request.toString()
            responseJson = requestPayload.response.toString()
            isEncryptionEnabled = requestPayload.isEncrypted
        }

        configurationRepository.save(configuration)

        return "Successfully created dummy api name"
    }

    fun addNewResponseCondition(
        pathName: String,
        requestPayload: AddNewResponseConditionRequestDTO
    ): String {

        val configuration = configurationRepository.findByName(pathName)?:return "Failed. Configuration name not found."

        val conditionCombinations = mutableListOf<Long>()

        requestPayload.condition.forEach {

            val condition: ConditionEntity = ConditionEntity().apply {
                this.configurationEntity = configuration
                conditionKey = it.key
                conditionValue = it.value
            }

            val savedConditionEntity = conditionRepository.save(condition)

            conditionCombinations.add(savedConditionEntity.id)

        }

        val conditionCombinationEntity: ConditionCombinationEntity = ConditionCombinationEntity().apply {
            conditionCombination = conditionCombinations
            response = requestPayload.responseToBeSaved.toString()
        }

        conditionCombinationRepository.save(conditionCombinationEntity)

        return "Successfully added new response for api name"
    }

    fun deleteApiNameAndAllConditions(name: String): String {

        return "Successfully deleted api name and all related conditions"
    }

    fun getResponse(name: String, requestPayload: String): String {

        val configuration = configurationRepository.findByName(name)?:return "Failed. Configuration name not found."

        val conditionCombinationEntitiesForConfiguration = conditionCombinationRepository.findByConfigurationEntity(configuration)

        val uniqueConditionCombination = mutableListOf<Long>()

        conditionCombinationEntitiesForConfiguration.forEach {

            if(!uniqueConditionCombination.containsAll(it.conditionCombination))
                for(element in it.conditionCombination)
                    if(!uniqueConditionCombination.contains(element))
                        uniqueConditionCombination.add(element)

        }

        val conditionsInCombinations = conditionRepository.findAllById(uniqueConditionCombination)

        val parsedRequestPayload =
            jacksonObjectMapper().readValue(
                requestPayload,
                object : TypeReference<Map<String, String>>() {}
            )

        val conditionCombinationFound = mutableListOf<Long>()

        parsedRequestPayload.forEach { (key, value) ->

            conditionsInCombinations.forEach {

                // Todo: Here will fail if value is json. Try to parse again.
                if (key == it.conditionKey && value == it.conditionValue)
                    conditionCombinationFound.add(it.id)
            }

        }

        conditionCombinationEntitiesForConfiguration.forEach {
            if (equalsIgnoreOrder(it.conditionCombination, conditionCombinationFound))
                return it.response
        }

        return configuration.responseJson
    }

    fun <T> equalsIgnoreOrder(list1:List<T>, list2:List<T>) = list1.size == list2.size && list1.toSet() == list2.toSet()

}