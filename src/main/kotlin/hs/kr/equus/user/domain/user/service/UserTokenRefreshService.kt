package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.UserInfo
import hs.kr.equus.user.domain.user.domain.repository.UserInfoRepository
import hs.kr.equus.user.global.security.jwt.JwtProperties
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserTokenRefreshService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties,
    private val userInfoRepository: UserInfoRepository
) {
    fun execute(refreshToken: String): TokenResponse {
        val tokenResponse = jwtTokenProvider.reIssue(refreshToken)

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
