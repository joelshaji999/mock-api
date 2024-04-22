package `in`.mockapi.repository

import `in`.mockapi.model.ConfigurationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConfigurationRepository : JpaRepository<ConfigurationEntity, Long> {

    fun save(configurationEntity: ConfigurationEntity)

    fun findByName(name: String): ConfigurationEntity?
}