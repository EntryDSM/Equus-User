package hs.kr.equus.user.global.security.jwt.token.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
