package hs.kr.equus.auth.global.error

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String?
) {
}