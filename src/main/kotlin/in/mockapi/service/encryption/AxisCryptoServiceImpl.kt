package `in`.mockapi.service.encryption

import `in`.mockapi.config.CryptographyConfig
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service("AxisCryptoServiceImpl")
class AxisCryptoServiceImpl(
    private val cryptographyConfig: CryptographyConfig,
) : CryptoService {


    override fun getEncryptedData(data: String, certificateFilePath: String?): String {
        try {
            val skeySpec = getKeySpec(cryptographyConfig.axisKey)

            val iv1 = byteArrayOf(
                0x8E.toByte(),
                0x12,
                0x39,
                0x9C.toByte(),
                0x07,
                0x72,
                0x6F,
                0x5A.toByte(),
                0x8E.toByte(),
                0x12,
                0x39,
                0x9C.toByte(),
                0x07,
                0x72,
                0x6F,
                0x5A.toByte()
            )
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(iv1)

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec)

            val encrypted = cipher.doFinal(data.toByteArray(charset("UTF-8")))

            val os = ByteArrayOutputStream()
            os.write(iv1)
            os.write(encrypted)
            val encryptedWithIV = os.toByteArray()

            return Base64.getEncoder().encodeToString(encryptedWithIV)
        } catch (ex: Exception) {
            throw Exception("Failed to encrypt data: $ex")
        }
    }

    override fun getDecryptedData(encryptedData: String, certificateFilePath: String?): String {
        try {
            val skeySpec = getKeySpec(cryptographyConfig.axisKey)

            val encryptedIVandTextAsBytes = Base64.getDecoder().decode(encryptedData)
            val iv = encryptedIVandTextAsBytes.copyOf(16)
            val ciphertextByte = encryptedIVandTextAsBytes.copyOfRange(16, encryptedIVandTextAsBytes.size)

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, IvParameterSpec(iv))
            val decryptedTextBytes = cipher.doFinal(ciphertextByte)

            return String(decryptedTextBytes, Charsets.UTF_8)
        } catch (ex: Exception) {
            throw Exception("Failed to decrypt data : $ex")
        }
    }

    private fun getKeySpec(key: String): SecretKeySpec {
        val baos = ByteArrayOutputStream(key.length / 2)

        for (i in key.indices step 2) {
            val output = key.substring(i, i + 2)
            val decimal = output.toInt(16)
            baos.write(decimal)
        }

        return SecretKeySpec(baos.toByteArray(), "AES")
    }

}