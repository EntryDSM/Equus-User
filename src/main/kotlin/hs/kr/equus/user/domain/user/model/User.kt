package hs.kr.equus.user.domain.user.model

import java.util.UUID

data class User(
    val id: UUID?,
    val phoneNumber: String,
    val name: String,
    val isStudent: Boolean,
    val receiptCode: UUID?
)
