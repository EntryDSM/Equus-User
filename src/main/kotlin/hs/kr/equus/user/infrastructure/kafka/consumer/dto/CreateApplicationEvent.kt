package hs.kr.equus.user.infrastructure.kafka.consumer.dto

import java.util.*

data class CreateApplicationEvent(
    val receiptCode: Long,
    val userId: UUID
)
