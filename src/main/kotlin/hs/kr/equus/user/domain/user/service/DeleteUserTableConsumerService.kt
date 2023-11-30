package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteUserTableConsumerService (
    private val userRepository: UserRepository
){
    @Transactional
    @KafkaListener(topics = arrayOf(KafkaTopics.DELETE_ALL_TABLE),  groupId = "\${kafka.consumer.groupId}")
    fun execute() = userRepository.deleteAll()
}
