package hs.kr.equus.user.global.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object InternalServerErrorException: EquusException(
    ErrorCode.INTERNAL_SERVER_ERROR
)