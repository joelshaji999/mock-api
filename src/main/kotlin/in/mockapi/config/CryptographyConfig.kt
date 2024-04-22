package `in`.mockapi.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cryptography.key")
data class CryptographyConfig(

    var axisKey: String = "",

    var iciciKey: String = ""

)
