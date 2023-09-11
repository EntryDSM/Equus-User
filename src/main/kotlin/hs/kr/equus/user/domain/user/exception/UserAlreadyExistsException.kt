package hs.kr.equus.user.domain.user.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object UserAlreadyExistsException: EquusException(
    ErrorCode.USER_ALREADY_EXISTS
)
