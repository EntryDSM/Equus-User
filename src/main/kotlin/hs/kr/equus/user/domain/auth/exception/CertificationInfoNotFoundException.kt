package hs.kr.equus.user.domain.auth.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object CertificationInfoNotFoundException : EquusException(
    ErrorCode.CERTIFICATION_INFO_NOT_FOUND
)
