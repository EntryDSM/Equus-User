package hs.kr.equus.user.domain.user.presentation.dto.response

import hs.kr.equus.user.domain.user.domain.UserRole
import java.util.UUID

data class InternalUserResponse(
    val id: UUID,
    val phoneNumber: String,
    val name: String,
    val isParent: Boolean,
    val receiptCode: UUID?,
    val role: UserRole
)
