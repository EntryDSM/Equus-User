package hs.kr.equus.user.domain.auth.service

import hs.kr.equus.user.domain.auth.domain.repository.CertificationInfoRepository
import hs.kr.equus.user.domain.auth.exception.CertificationInfoNotFoundException
import hs.kr.equus.user.domain.auth.exception.CertificationInfoUnAuthorizeException
import hs.kr.equus.user.domain.auth.presentation.dto.request.CheckCertificationInfoRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CheckCertificationInfoService(
    private val certificationInfoRepository: CertificationInfoRepository
) {
    fun execute(checkCertificationInfoRequest: CheckCertificationInfoRequest) {
        val certificationInfo = certificationInfoRepository.findByIdOrNull(checkCertificationInfoRequest.phoneNumber)
            ?: throw CertificationInfoNotFoundException

        if (certificationInfo.certificationNumber != checkCertificationInfoRequest.certificationNumber) {
            throw CertificationInfoUnAuthorizeException
        }

        certificationInfo.confirmCertification()
        certificationInfoRepository.save(certificationInfo)
    }
}
