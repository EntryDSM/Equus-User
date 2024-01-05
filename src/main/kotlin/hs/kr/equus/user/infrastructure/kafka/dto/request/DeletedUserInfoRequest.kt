package hs.kr.equus.user.infrastructure.kafka.dto.request

import java.util.UUID

data class DeletedUserInfoRequest(
    val userId: UUID
)
