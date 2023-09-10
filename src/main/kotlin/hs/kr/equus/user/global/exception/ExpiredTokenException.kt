package hs.kr.equus.user.global.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object ExpiredTokenException: EquusException(
    ErrorCode.EXPIRED_TOKEN
)
