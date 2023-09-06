package hs.kr.equus.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EquusAuthApplication

fun main(args: Array<String>) {
    runApplication<EquusAuthApplication>(*args)
}
