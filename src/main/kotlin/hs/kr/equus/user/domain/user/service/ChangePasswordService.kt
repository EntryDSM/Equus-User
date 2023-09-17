package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.auth.domain.repository.PassInfoRepository
import hs.kr.equus.user.domain.auth.exception.PassInfoNotFoundException
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.presentation.dto.request.ChangePasswordRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePasswordService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val passInfoRepository: PassInfoRepository
) {
    @Transactional
    fun execute(changePasswordRequest: ChangePasswordRequest) {
        changePasswordRequest.phoneNumber.takeIf { passInfoRepository.existsByPhoneNumber(it) }
            ?.let { phoneNumber ->
                userRepository.findByPhoneNumber(phoneNumber)
                    .orElseThrow { UserNotFoundException }
                    .changePassword(passwordEncoder.encode(changePasswordRequest.newPassword))
            } ?: throw PassInfoNotFoundException
    }
}
