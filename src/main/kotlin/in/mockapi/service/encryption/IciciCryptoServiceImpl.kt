package `in`.mockapi.service.encryption

import `in`.mockapi.config.CryptographyConfig
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.io.pem.PemReader
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.security.*
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service("IciciCryptoServiceImpl")
class IciciCryptoServiceImpl(
    private val cryptographyConfig: CryptographyConfig
) : CryptoService {

    override fun getEncryptedData(data: String, certificateFilePath: String?): String {
        certificateFilePath ?: throw Exception("certificateFilePath not found")
        val key: SecretKey = generateAESKey()
        val publicKey: PublicKey = loadPublicKey(certificateFilePath)
        val encryptedKey: ByteArray = rsaEncrypt(key.encoded, publicKey)
        val encryptedKeyString = Base64.getEncoder().encodeToString(encryptedKey)
        val iv: IvParameterSpec = generateIV()
        val ivString = Base64.getEncoder().encodeToString(iv.iv)
        val encryptedData: ByteArray = aesEncrypt(data, key, iv)
        val encryptedString: String = Base64.getEncoder().encodeToString(encryptedData)
        return encryptedString
    }

    override fun getDecryptedData(data: String, certificateFilePath: String?): String {
        certificateFilePath ?: throw Exception("certificateFilePath not found")
        Security.addProvider(BouncyCastleProvider())
        val dataBytes: ByteArray = Base64.getDecoder().decode(data)
        val ivBytes: ByteArray = dataBytes.copyOfRange(IVY_START, IVY_END)
        val iv = IvParameterSpec(ivBytes)
        val encryptedData: ByteArray = dataBytes.copyOfRange(IVY_END, dataBytes.size)
        val privateKey: PrivateKey = loadPrivateKey(certificateFilePath)
        val key: ByteArray = rsaDecrypt(cryptographyConfig.iciciKey, privateKey)
        val secretKeySpec = SecretKeySpec(key, 0, key.size, "AES")
        val decryptedData: ByteArray = aesDecrypt(encryptedData, secretKeySpec, iv)
        return String(decryptedData)
    }

    private fun aesEncrypt(data: String, secret: SecretKey, iv: IvParameterSpec): ByteArray {
        val dataBytes: ByteArray = data.toByteArray()
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secret, iv)
        return cipher.doFinal(dataBytes)
    }

    private fun aesDecrypt(data: ByteArray, secret: SecretKey, iv: IvParameterSpec): ByteArray {
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secret, iv)
        return cipher.doFinal(data)
    }

    private fun rsaEncrypt(data: ByteArray, publicKey: PublicKey): ByteArray {
        val keyCipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        keyCipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return keyCipher.doFinal(data)
    }

    private fun rsaDecrypt(payload: String, privateKey: PrivateKey): ByteArray {
        val data: ByteArray = Base64.getDecoder().decode(payload)
        val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }

    private fun generateAESKey(): SecretKey {
        val generator: KeyGenerator = KeyGenerator.getInstance("AES")
        generator.init(AES_KEY_SIZE)
        return generator.generateKey()
    }

    private fun generateIV(): IvParameterSpec {
        val rnd = SecureRandom()
        val seed: ByteArray = rnd.generateSeed(SEED_NUM_BYTE)
        return IvParameterSpec(seed)
    }

    private fun loadPrivateKey(privateKeyFile: String): PrivateKey {
        Security.addProvider(BouncyCastleProvider())
        val file = FileInputStream(privateKeyFile)
        val pemReader = PemReader(file.reader())
        val spec = PKCS8EncodedKeySpec(pemReader.readPemObject().content)
        val kf: KeyFactory = KeyFactory.getInstance("RSA", "BC")
        return kf.generatePrivate(spec)
    }

    private fun loadPublicKey(certificateFilePath: String): PublicKey {
        val file = FileInputStream(certificateFilePath)
        val certificateFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
        val x509Certificate: X509Certificate =
            certificateFactory.generateCertificate(file) as X509Certificate
        return x509Certificate.publicKey
    }

    companion object {
        const val IVY_START = 0
        const val IVY_END = 16
        const val AES_KEY_SIZE = 128
        const val SEED_NUM_BYTE = 16
    }
}