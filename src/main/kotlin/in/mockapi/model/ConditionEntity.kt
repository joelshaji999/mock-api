package `in`.mockapi.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity(name = "condition")
class ConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    lateinit var configurationEntity: ConfigurationEntity

    @Column(name = "condition_key")
    lateinit var conditionKey: String

    @Column(name = "condition_value")
    lateinit var conditionValue: String

}
