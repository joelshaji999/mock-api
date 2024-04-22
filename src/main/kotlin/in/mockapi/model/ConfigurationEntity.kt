package `in`.mockapi.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity(name = "configuration")
class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @Column(unique = true)
    lateinit var name: String

    @Column(name = "request_json", columnDefinition = "TEXT")
    lateinit var requestJson: String

    @Column(name = "response_json", columnDefinition = "TEXT")
    lateinit var responseJson: String

    @Column(name = "is_encryption_enabled")
    var isEncryptionEnabled: Boolean = false

    @Column(name = "crypto_service")
    var cryptoService: String? = null

    @Column(name = "encrypted_request_key")
    var encryptedRequestKey: String? = null

    @Column(name = "encrypted_response_key")
    var encryptedResponseKey: String? = null

}
