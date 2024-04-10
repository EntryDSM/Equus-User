package hs.kr.equus.user.domain.admin.service

import hs.kr.equus.user.domain.admin.exception.AdminNotFoundException
import hs.kr.equus.user.domain.admin.presentation.dto.request.AdminLoginRequest
import hs.kr.equus.user.domain.admin.domain.repository.AdminRepository
import hs.kr.equus.user.domain.user.domain.UserInfo
import hs.kr.equus.user.domain.user.domain.UserRole
import hs.kr.equus.user.domain.user.domain.repository.UserInfoRepository
import hs.kr.equus.user.domain.user.exception.PasswordNotValidException
import hs.kr.equus.user.global.property.JwtProperties
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.security.jwt.token.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLoginService(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userInfoRepository: UserInfoRepository,
    private val jwtProperties: JwtProperties
) {
    @Transactional
    fun execute(
        adminLoginRequest: AdminLoginRequest
    ): TokenResponse {
        val admin = adminRepository.findByAdminId(adminLoginRequest.adminId) ?: throw AdminNotFoundException

        if (!passwordEncoder.matches(adminLoginRequest.password, admin.password)) {
            throw PasswordNotValidException
        }
        val tokenResponse = jwtTokenProvider.generateToken(admin.id.toString(), UserRole.ADMIN.toString())
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
