package hs.kr.equus.user.global.utils.sms

import hs.kr.equus.user.global.property.SmsProperties
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.apache.http.client.HttpResponseException
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.random.Random

@Component
class SMSUtil(
    private val messageService: DefaultMessageService,
    private val smsProperties: SmsProperties
) {
    fun sendCertificationMessage(phoneNumber: String): Int {
        val certificationNumber = createRandomCertificationNumber()

        val message = Message(
            from = smsProperties.callerId,
            to = phoneNumber,
            text = createCertificationMessage(certificationNumber)
        )

        val response = messageService.sendOne(SingleMessageSendingRequest(message))
            ?: throw IOException("SMS 서비스 연결 실패")

        if (response.statusCode.toInt() in 400..500) {
            throw HttpResponseException(response.statusCode.toInt(), "잘못된 요청 또는 서버 오류")
        }

        return certificationNumber
    }

    fun createCertificationMessage(certificationNumber: Int): String {
        return "대덕소프트웨어마이스터고등학교 인증번호 [$certificationNumber] 타인에게 절대 알리지 마세요"
    }

    fun createRandomCertificationNumber(): Int {
        return Random.nextInt(100000, 1000000)
    }
}
