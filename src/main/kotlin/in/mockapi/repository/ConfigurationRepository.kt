package `in`.mockapi.repository

import `in`.mockapi.model.ConfigurationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ConfigurationRepository : JpaRepository<ConfigurationEntity, Long> {

    fun save(configurationEntity: ConfigurationEntity)

    fun findByName(name: String): ConfigurationEntity?

    @Query("select distinct name from configuration;", nativeQuery = true)
    fun findAllNames(): List<String>
}