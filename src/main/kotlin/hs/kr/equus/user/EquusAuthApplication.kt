package hs.kr.equus.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EquusAuthApplication

fun main(args: Array<String>) {
    runApplication<EquusAuthApplication>(*args)
}
