package hs.kr.equus.user.infrastructure.kafka.consumer

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.facade.UserFacade
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserWithdrawalConsumer(
    val userFacade: UserFacade,
    val userRepository: UserRepository
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-user",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute() {
        val user = userFacade.getCurrentUser()
        userRepository.deleteById(user.id)
    }
}
