package `in`.mockapi.dto

data class AddNewResponseConditionRequestDTO(

    val responseToBeSaved: Map<String, Any>,

    val condition: Map<String, String>

)
