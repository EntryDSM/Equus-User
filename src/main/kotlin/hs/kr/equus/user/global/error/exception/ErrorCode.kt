package hs.kr.equus.user.global.error.exception

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    // UnAuthorization
    INVALID_TOKEN(401, "COMMON401-0", "Invalid Token"),
    EXPIRED_TOKEN(401, "COMMON401-1", "Expired Token"),
    UNAUTHENTICATED(401, "COMMON401-2", "UnAuthenticated"),
    INVALID_URL(401, "URL401-0", "Invalid Url"),
    INVALID_PASS(401, "PASS_INFO-401-0", "Invalid Pass"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "COMMON500-0", "Internal Server Error"),
    INVALID_OKCERT_CONNECTION(500, "PASS_INFO-500-0", "Invalid OkCert Connection")
}
