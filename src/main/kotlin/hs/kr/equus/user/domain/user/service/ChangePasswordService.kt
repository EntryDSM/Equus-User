package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.auth.domain.repository.CertificationInfoRepository
import hs.kr.equus.user.domain.auth.exception.CertificationInfoNotFoundException
import hs.kr.equus.user.domain.auth.exception.CertificationInfoUnAuthorizeException
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.presentation.dto.request.ChangePasswordRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePasswordService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val certificationInfoRepository: CertificationInfoRepository
) {
    @Transactional
    fun execute(changePasswordRequest: ChangePasswordRequest) {
        val phoneNumber = changePasswordRequest.phoneNumber

        val certificationInfo = certificationInfoRepository.findByIdOrNull(phoneNumber)
            ?: throw CertificationInfoNotFoundException

        if (!certificationInfo.isValid) {
            throw CertificationInfoUnAuthorizeException
        }
        userRepository.findByPhoneNumber(phoneNumber)
            ?.updatePassword(passwordEncoder.encode(changePasswordRequest.newPassword))
            ?: throw UserNotFoundException
    }
}
