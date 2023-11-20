package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.UserInfo
import hs.kr.equus.user.domain.user.domain.UserRole
import hs.kr.equus.user.domain.user.domain.repository.UserInfoRepository
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.PasswordNotValidException
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.presentation.dto.request.UserLoginRequest
import hs.kr.equus.user.global.security.jwt.JwtProperties
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserLoginService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtProperties: JwtProperties,
    private val userInfoRepository: UserInfoRepository
) {
    @Transactional
    fun execute(userLoginRequest: UserLoginRequest): TokenResponse {
        val user = userRepository.findByPhoneNumber(userLoginRequest.phoneNumber) ?: throw UserNotFoundException

        if (!passwordEncoder.matches(userLoginRequest.password, user.password)) {
            throw PasswordNotValidException
        }
        val tokenResponse = jwtTokenProvider.generateToken(user.id.toString(), user.role.toString())
        val userInfo = UserInfo(
            token = tokenResponse.accessToken,
            userId = jwtTokenProvider.getSubjectWithExpiredCheck(tokenResponse.accessToken),
            userRole = jwtTokenProvider.getRole(tokenResponse.accessToken),
            ttl = jwtProperties.accessExp
        )
        userInfoRepository.save(userInfo)
        return tokenResponse
    }
}
