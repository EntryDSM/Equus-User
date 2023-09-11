package hs.kr.equus.user.domain.auth.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object PassInfoNotFoundException : EquusException(
    ErrorCode.PASS_INFO_NOT_FOUND
)
