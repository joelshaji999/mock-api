package `in`.mockapi.service

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.context.annotation.Bean

class CustomObjectMapper {

//    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(CustomModule())
        return objectMapper
    }

    class CustomModule : com.fasterxml.jackson.databind.module.SimpleModule() {
        init {
            addSerializer(String::class.java, CustomStringSerializer())
        }
    }

    class CustomStringSerializer : JsonSerializer<String>() {
        override fun serialize(
            value: String?,
            gen: JsonGenerator?,
            serializers: SerializerProvider?
        ) {
            if (value != null && value == "StringToExclude") {
                // Skip serialization of the string "StringToExclude"
                return
            }
            gen?.writeString(value)
        }
    }
}