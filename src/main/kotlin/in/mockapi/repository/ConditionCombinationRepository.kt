package `in`.mockapi.repository

import `in`.mockapi.model.ConditionCombinationEntity
import `in`.mockapi.model.ConfigurationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConditionCombinationRepository : JpaRepository<ConditionCombinationEntity, Long> {

    fun findByConfigurationEntity(configurationEntity: ConfigurationEntity): MutableList<ConditionCombinationEntity>

    fun deleteByConfigurationEntity(configurationEntity: ConfigurationEntity)

}