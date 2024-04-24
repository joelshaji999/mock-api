package `in`.mockapi.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import `in`.mockapi.dto.ConditionData
import `in`.mockapi.dto.ListAllResponseDTO
import `in`.mockapi.model.ConditionCombinationEntity
import `in`.mockapi.model.ConditionEntity
import `in`.mockapi.model.ConfigurationEntity
import `in`.mockapi.repository.ConditionCombinationRepository
import `in`.mockapi.repository.ConditionRepository
import org.springframework.stereotype.Service

@Service
class DummyApiLogicService(
    private val conditionRepository: ConditionRepository,
    private val conditionCombinationRepository: ConditionCombinationRepository,
) {

    fun getResponse(configuration: ConfigurationEntity, requestPayload: Map<String, Any>): String {

        val conditionCombinations = conditionCombinationRepository.findByConfigurationEntity(configuration)

        val conditionEntityIds = getUniqueConditionsFromConditionCombinations(conditionCombinations)

        val conditionEntities = conditionRepository.findAllById(conditionEntityIds)

        val conditionCombinationFound = getConditionCombination(requestPayload, conditionEntities)

        conditionCombinations.forEach {
            if (equalsIgnoreOrder(it.conditionCombination, conditionCombinationFound))
                return it.response
        }

        return configuration.responseJson
    }

    fun getList(configuration: ConfigurationEntity, name: String): ListAllResponseDTO {

        val conditionCombinations = conditionCombinationRepository.findByConfigurationEntity(configuration)

        val conditionEntityIds = getUniqueConditionsFromConditionCombinations(conditionCombinations)

        val conditionEntities = conditionRepository.findAllById(conditionEntityIds)

        val condition = mutableListOf<ConditionData>()

        conditionCombinations.forEach {

            val conditionMap: MutableMap<String, Any> = mutableMapOf()

            it.conditionCombination.forEach {

                val conditionEntity = conditionEntities.filter { conditionEntity -> conditionEntity.id == it }

                conditionMap[conditionEntity.first().conditionKey] = conditionEntity.first().conditionValue

            }

            val conditionData = ConditionData(
                conditionMap = conditionMap,
                responseData = it.response
            )

            condition.add(conditionData)
        }

        val response = ListAllResponseDTO(
            name = name,
            condition = condition
        )

        return response
    }

    private fun getUniqueConditionsFromConditionCombinations(conditionCombinations: MutableList<ConditionCombinationEntity>): List<Long> {

        val conditions = mutableListOf<Long>()

        conditionCombinations.forEach {
            conditions.addAll(it.conditionCombination)
        }

        return conditions.distinct()
    }

    private fun getConditionCombination(
        requestPayload: Map<String, Any>,
        conditions: MutableList<ConditionEntity>,
    ): MutableList<Long> {

        var conditionCombinationFound: MutableList<Long> = mutableListOf()

        requestPayload.forEach { (key, value) ->

            val parsedValue = jacksonObjectMapper().writeValueAsString(value)

            if (isJsonValid(parsedValue)) {

                val innerJsonValue =
                    jacksonObjectMapper().readValue(parsedValue, object : TypeReference<Map<String, Any>>() {})

                conditionCombinationFound.addAll(getConditionCombination(innerJsonValue, conditions))

            }

            conditions.forEach {

                if (key == it.conditionKey && value == it.conditionValue) {

                    conditionCombinationFound.add(it.id)

                }

            }
        }

        return conditionCombinationFound
    }

    private fun isJsonValid(jsonInString: String): Boolean {
        try {

            jacksonObjectMapper().readValue(jsonInString, object : TypeReference<Map<String, Any>>() {})

            return true

        } catch (ex: Exception) {

            return false

        }
    }

    private fun <T> equalsIgnoreOrder(list1: List<T>, list2: List<T>) =
        list1.size == list2.size && list1.toSet() == list2.toSet()

}