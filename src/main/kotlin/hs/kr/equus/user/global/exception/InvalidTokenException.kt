package hs.kr.equus.user.global.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object InvalidTokenException: EquusException(
    ErrorCode.INVALID_TOKEN
)
