package hs.kr.equus.user.domain.user.presentation.dto.response

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val phoneNumber: String,
    val name: String,
    val isStudent: Boolean,
    val receiptCode: UUID?
)
