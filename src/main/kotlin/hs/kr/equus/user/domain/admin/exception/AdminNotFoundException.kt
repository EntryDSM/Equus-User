package hs.kr.equus.user.domain.admin.exception

import hs.kr.equus.user.global.error.exception.EquusException
import hs.kr.equus.user.global.error.exception.ErrorCode

object AdminNotFoundException : EquusException(
    ErrorCode.ADMIN_NOT_FOUND
)
