package hs.kr.equus.user.infrastructure.kafka.producer

import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.user.infrastructure.kafka.dto.DeleteUserEventRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteUserProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, DeleteUserEventRequest>
) : DeleteUserProducer {
    override fun send(userId: UUID?) {
        kafkaTemplate.send(KafkaTopics.DELETE_USER, DeleteUserEventRequest(userId))
    }
}
