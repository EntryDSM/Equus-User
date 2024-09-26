package hs.kr.equus.user.infrastructure.kafka.producer

interface DeleteUserProducer {
    fun send(receiptCode: Long)
}
