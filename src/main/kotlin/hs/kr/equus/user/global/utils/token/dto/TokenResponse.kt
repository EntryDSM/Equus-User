package hs.kr.equus.user.global.utils.token.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
