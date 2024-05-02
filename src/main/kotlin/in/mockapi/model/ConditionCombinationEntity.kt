package `in`.mockapi.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity(name = "condition_combination")
class ConditionCombinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: ZonedDateTime = ZonedDateTime.now()

    @Column(name = "condition_combination")
    lateinit var conditionCombination: List<Long>

    @Column(columnDefinition = "TEXT")
    lateinit var response: String

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    lateinit var configurationEntity: ConfigurationEntity

    @Column(name = "uuid")
    lateinit var uuid: String

}