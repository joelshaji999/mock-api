package `in`.mockapi.repository

import `in`.mockapi.model.ConditionEntity
import `in`.mockapi.model.ConfigurationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConditionRepository : JpaRepository<ConditionEntity, Long> {

    fun findByConditionKeyAndConditionValue(conditionKey:String, conditionValue: String): ConditionEntity?

    fun deleteAllByConfigurationEntity(configurationEntity: ConfigurationEntity)

}