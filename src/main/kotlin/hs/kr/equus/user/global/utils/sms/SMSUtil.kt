package hs.kr.equus.user.global.utils.sms

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
object SMSUtil {
    fun createCertificationMessage(certificationNumber: Int): String {
        return "대덕소프트웨어마이스터고등학교 인증번호 [$certificationNumber] 타인에게 절대 알리지 마세요"
    }

    fun createRandomCertificationNumber(): Int {
        return Random.nextInt(100000, 1000000)
    }
}
