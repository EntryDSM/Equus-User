package hs.kr.equus.user.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.user.domain.user.service.ChangeReceiptCodeService
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.user.infrastructure.kafka.consumer.dto.CreateApplicationEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateApplicationConsumer(
    private val changeReceiptCodeService: ChangeReceiptCodeService,
    private val mapper: ObjectMapper,
) {
    @KafkaListener(
        topics = [KafkaTopics.CREATE_APPLICATION],
        groupId = "change-user-receipt-code",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun execute(message: String) {
        val createApplicationEvent = mapper.readValue(message, CreateApplicationEvent::class.java)
        changeReceiptCodeService.execute(createApplicationEvent.userId, createApplicationEvent.receiptCode)
    }
}
