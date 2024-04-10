package hs.kr.equus.user.domain.auth.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object CertificationInfoUnAuthorizeException : EquusException(
    ErrorCode.CERTIFICATION_INFO_UNAUTHORIZED
)
