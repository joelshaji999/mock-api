package `in`.mockapi.dto

data class AddNewRequestDTO(

    val name: String,

    val request: Map<String, Any>,

    val response: Map<String, Any>,

    val isEncrypted: Boolean,

    val encryptionService: String?,

    val encryptedRequestKey: String?,

    val encryptedResponseKey: String?

)
