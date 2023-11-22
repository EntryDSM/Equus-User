package hs.kr.equus.user.domain.admin.service

import hs.kr.equus.user.domain.admin.exception.AdminNotFoundException
import hs.kr.equus.user.domain.admin.presentation.dto.request.AdminLoginRequest
import hs.kr.equus.user.domain.admin.domain.repository.AdminRepository
import hs.kr.equus.user.domain.user.domain.UserRole
import hs.kr.equus.user.domain.user.exception.PasswordNotValidException
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLoginService(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional(readOnly = true)
    fun execute(
        adminLoginRequest: AdminLoginRequest
    ): TokenResponse {
        val admin = adminRepository.findByIdOrNull(adminLoginRequest.adminId) ?: throw AdminNotFoundException

        if (!passwordEncoder.matches(adminLoginRequest.password, admin.password)) {
            throw PasswordNotValidException
        }

        return jwtTokenProvider.generateToken(admin.adminId, UserRole.ADMIN.toString())
    }
}
