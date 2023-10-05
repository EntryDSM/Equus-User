package hs.kr.equus.user.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // UnAuthorization
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_URL(401, "Invalid Url"),
    INVALID_PASS(401, "Invalid Pass"),
    INVALID_USER_PASSWORD(401, "Invalid User Password"),
    ADMIN_UNAUTHORIZED(401, "Admin UnAuthorized"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INVALID_OKCERT_CONNECTION(500, "Invalid OkCert Connection"),

    // Not Found
    USER_NOT_FOUND(404, "User Not Found"),
    PASS_INFO_NOT_FOUND(404, "Pass Info Not Found"),
    ADMIN_NOT_FOUND(404, "Admin Not Found"),

    // Conflict
    USER_ALREADY_EXISTS(409, "User Already Exists")
}
