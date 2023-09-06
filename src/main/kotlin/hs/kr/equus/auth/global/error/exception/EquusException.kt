package hs.kr.equus.auth.global.error.exception

import java.lang.RuntimeException

abstract class EquusException(
    val errorCode: ErrorCode
): RuntimeException() {
}