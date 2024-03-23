package hs.kr.equus.user.infrastructure.kafka.producer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.user.infrastructure.kafka.dto.request.DeletedUserInfoRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteUserProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : DeleteUserProducer {
    override fun send(userId: UUID?) {
        val deletedUserInfoRequest = DeletedUserInfoRequest(userId!!)
        kafkaTemplate.send(KafkaTopics.DELETE_USER, objectMapper.writeValueAsString(deletedUserInfoRequest))
    }
}
