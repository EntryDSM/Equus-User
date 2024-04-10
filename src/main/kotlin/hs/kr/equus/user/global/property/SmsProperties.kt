package hs.kr.equus.user.global.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("auth.sms")
class SmsProperties(
    val apiKey: String,
    val apiSecretKey: String,
    val domain: String,
    val callerId: String
)
