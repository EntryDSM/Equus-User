package hs.kr.equus.user.global.config.sms

import hs.kr.equus.user.global.property.SmsProperties
import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SMSConfiguration(
    private val smsProperties: SmsProperties
) {
    @Bean
    fun messageService(): DefaultMessageService =
        initialize(
            apiKey = smsProperties.apiKey,
            apiSecretKey = smsProperties.apiSecretKey,
            domain = smsProperties.domain
        )
}
