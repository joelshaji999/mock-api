package `in`.mockapi.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.annotation.JsonDeserialize



data class ListAllResponseDTO(

    val name: String,

    val condition: List<ConditionData>
)

data class ConditionData(

    var conditionMap: Map<String, Any>,

    @JsonRawValue
    var responseData: String

)
