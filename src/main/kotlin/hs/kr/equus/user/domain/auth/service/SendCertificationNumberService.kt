package hs.kr.equus.user.domain.auth.service

import hs.kr.equus.user.domain.auth.domain.CertificationInfo
import hs.kr.equus.user.domain.auth.domain.repository.CertificationInfoRepository
import hs.kr.equus.user.domain.auth.exception.InvalidSMSConnectException
import hs.kr.equus.user.domain.auth.exception.SMSBadRequestException
import hs.kr.equus.user.global.property.SmsProperties
import hs.kr.equus.user.global.utils.sms.SMSUtil
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class SendCertificationNumberService(
    private val messageService: DefaultMessageService,
    private val smsProperties: SmsProperties,
    private val certificationInfoRepository: CertificationInfoRepository
) {
    fun execute(phoneNumber: String) {
        val certificationNumber = SMSUtil.createRandomCertificationNumber()

        val message = Message(
            from = smsProperties.callerId,
            to = phoneNumber,
            text = SMSUtil.createCertificationMessage(certificationNumber)
        )

        val response = messageService.sendOne(SingleMessageSendingRequest(message))
            ?: throw InvalidSMSConnectException

        if (response.statusCode.toInt() in 400..500) {
            throw SMSBadRequestException
        }

        certificationInfoRepository.save(
            CertificationInfo(
                phoneNumber = phoneNumber,
                certificationNumber = certificationNumber,
                isValid = false,
                ttl = 300L
            )
        )
    }
}
