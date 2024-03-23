package hs.kr.equus.user.infrastructure.kafka.producer

import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class DeleteAllTableProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, Unit>
) : DeleteAllTableProducer {
    override fun send() {
        kafkaTemplate.send(KafkaTopics.DELETE_ALL_TABLE, Unit)
    }
}
