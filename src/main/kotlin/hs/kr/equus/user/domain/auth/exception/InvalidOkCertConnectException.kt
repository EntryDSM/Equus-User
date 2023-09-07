package hs.kr.equus.user.domain.auth.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object InvalidOkCertConnectException: EquusException(
    ErrorCode.INVALID_OKCERT_CONNECTION
)