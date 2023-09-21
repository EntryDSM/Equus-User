package hs.kr.equus.user.infrastructure.kafka.producer

import java.util.UUID

interface DeleteUserProducer {
    fun send(userId: UUID?)
}
