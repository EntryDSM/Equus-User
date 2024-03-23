package hs.kr.equus.user.domain.user.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object PasswordNotValidException : EquusException(
    ErrorCode.INVALID_USER_PASSWORD
)
