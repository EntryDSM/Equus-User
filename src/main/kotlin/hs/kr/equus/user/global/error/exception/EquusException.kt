package hs.kr.equus.user.global.error.exception

import java.lang.RuntimeException

abstract class EquusException(
    val errorCode: ErrorCode
) : RuntimeException()
