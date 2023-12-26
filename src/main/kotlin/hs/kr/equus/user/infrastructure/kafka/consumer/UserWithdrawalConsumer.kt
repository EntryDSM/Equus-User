package hs.kr.equus.user.infrastructure.kafka.consumer

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserWithdrawalConsumer(
    val userRepository: UserRepository
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-user",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute(userId: UUID?) = userRepository.deleteById(userId)
}
