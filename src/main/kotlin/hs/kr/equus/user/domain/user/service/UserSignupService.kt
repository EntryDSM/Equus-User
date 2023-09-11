package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.auth.domain.repository.PassInfoRepository
import hs.kr.equus.user.domain.auth.exception.PassInfoNotFoundException
import hs.kr.equus.user.domain.user.domain.Role
import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserAlreadyExistsException
import hs.kr.equus.user.domain.user.presentation.dto.request.UserSignupRequest
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignupService(
    private val userRepository: UserRepository,
    private val passInfoRepository: PassInfoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: JwtTokenProvider
) {

    @Transactional
    fun execute(userSignupRequest: UserSignupRequest): TokenResponse {
        val telephoneNumber = userSignupRequest.telephoneNumber
        val password = passwordEncoder.encode(userSignupRequest.password)

        if (userRepository.existsByTelephoneNumber(telephoneNumber)) {
            throw UserAlreadyExistsException
        }

        val passInfo = passInfoRepository.findByPhoneNumber(telephoneNumber).orElseThrow { PassInfoNotFoundException }

        val user = User(
            id = null,
            telephoneNumber = passInfo.phoneNumber,
            password = password,
            name = passInfo.name,
            isStudent = userSignupRequest.isStudent,
            entryInfoId = null
        )

        userRepository.save(user)

        return tokenProvider.generateToken(
            user.telephoneNumber,
            Role.USER.toString()
        )
    }
}
