package `in`.mockapi.dto

data class ListAllResponseDTO(

    val name: String,

    val condition: List<ConditionData>
)

data class ConditionData(

    var conditionMap: Map<String, Any>,

    var responseData: String

)
