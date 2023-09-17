package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.Role
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.PasswordNotValidException
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.presentation.dto.request.UserLoginRequest
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    fun execute(userLoginRequest: UserLoginRequest): TokenResponse {
        val user = userRepository.findByPhoneNumber(userLoginRequest.phoneNumber)
            .orElseThrow { UserNotFoundException }

        if (!passwordEncoder.matches(userLoginRequest.password, user.password)) {
            throw PasswordNotValidException
        }

        return jwtTokenProvider.generateToken(user.phoneNumber, Role.USER.toString())
    }
}
