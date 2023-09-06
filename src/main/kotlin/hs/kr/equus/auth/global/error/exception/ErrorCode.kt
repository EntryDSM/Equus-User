package hs.kr.equus.auth.global.error.exception

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    // UnAuthorization
    INVALID_TOKEN(401, "COMMON401-0", "Invalid Token"),
    EXPIRED_TOKEN(401, "COMMON401-1", "Expired Token"),
    UNAUTHENTICATED(401, "COMMON401-2", "UnAuthenticated"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "COMMON500-0", "Internal Server Error")
}