package hs.kr.equus.user.infrastructure.kafka.dto

import java.util.UUID

data class DeleteUserEventRequest(
    val userId: UUID?
)
