package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.stereotype.Service

@Service
class UserTokenRefreshService(
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun execute(refreshToken: String): TokenResponse = jwtTokenProvider.reIssue(refreshToken)
}
