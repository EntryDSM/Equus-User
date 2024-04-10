package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.auth.domain.repository.CertificationInfoRepository
import hs.kr.equus.user.domain.auth.exception.CertificationInfoNotFoundException
import hs.kr.equus.user.domain.auth.exception.CertificationInfoUnAuthorizeException
import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.domain.UserRole
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserAlreadyExistsException
import hs.kr.equus.user.domain.user.presentation.dto.request.UserSignupRequest
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserSignupService(
    private val userRepository: UserRepository,
    private val certificationInfoRepository: CertificationInfoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: JwtTokenProvider
) {
    fun execute(userSignupRequest: UserSignupRequest): TokenResponse {
        val phoneNumber = userSignupRequest.phoneNumber
        val password = passwordEncoder.encode(userSignupRequest.password)

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw UserAlreadyExistsException
        }

        val certificationInfo = certificationInfoRepository.findByIdOrNull(phoneNumber)
            ?: throw CertificationInfoNotFoundException

        if (!certificationInfo.isValid) {
            throw CertificationInfoUnAuthorizeException
        }

        val user = User(
            id = null,
            phoneNumber = userSignupRequest.phoneNumber,
            password = password,
            name = userSignupRequest.name,
            isParent = userSignupRequest.isParent,
            receiptCode = null,
            role = UserRole.USER
        )

        userRepository.save(user)

        certificationInfoRepository.deleteById(phoneNumber)

        return tokenProvider.generateToken(
            user.phoneNumber,
            user.role.toString()
        )
    }
}
