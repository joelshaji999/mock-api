package `in`.mockapi.service.encryption

interface CryptoService {

    fun getEncryptedData(
        data: String,
        certificateFilePath: String?,
    ): String

    fun getDecryptedData(
        encryptedData: String,
        certificateFilePath: String?,
    ): String

}