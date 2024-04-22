package `in`.mockapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DummybankApplication

fun main(args: Array<String>) {
	runApplication<DummybankApplication>(*args)
}
