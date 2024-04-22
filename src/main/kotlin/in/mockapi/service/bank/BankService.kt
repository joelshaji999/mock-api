package `in`.mockapi.service.bank

interface BankService {

    fun getEncryptedRequest(
        bankName: String,
        requestPayload: String
    ): String

}