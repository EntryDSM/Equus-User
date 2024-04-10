package hs.kr.equus.user.domain.auth.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object InvalidSMSConnectException : EquusException(
    ErrorCode.INVALID_SMS_CONNECTION
)
