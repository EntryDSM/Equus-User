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
import org.apache.http.client.HttpResponseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException

@Transactional
@Service
class SendCertificationNumberService(
    private val smsUtil: SMSUtil,
    private val certificationInfoRepository: CertificationInfoRepository
) {
    fun execute(phoneNumber: String) {
        val certificationNumber: Int

        try {
            certificationNumber = smsUtil.sendCertificationMessage(phoneNumber)
        } catch (e: IOException) {
            throw InvalidSMSConnectException
        } catch (e: HttpResponseException) {
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
