package hs.kr.equus.user.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // UnAuthorization
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_USER_PASSWORD(401, "Invalid User Password"),
    ADMIN_UNAUTHORIZED(401, "Admin UnAuthorized"),
    CERTIFICATION_INFO_UNAUTHORIZED(401, "Certification Info UnAuthorized"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INVALID_SMS_CONNECTION(500, "Invalid SMS Connection"),

    // Not Found
    USER_NOT_FOUND(404, "User Not Found"),
    ADMIN_NOT_FOUND(404, "Admin Not Found"),
    CERTIFICATION_INFO_NOT_FOUND(404, "Certification Info Not Found"),

    // Conflict
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    // Bad Request
    SMS_BAD_REQUEST(400, "SMS Bad Request")
}
