package `in`.mockapi.dto

import com.fasterxml.jackson.annotation.JsonRawValue

data class ApiResponseBodyDTO(

    val statusCode: Int,

    val status: String,

    @JsonRawValue
    val message: String
)
