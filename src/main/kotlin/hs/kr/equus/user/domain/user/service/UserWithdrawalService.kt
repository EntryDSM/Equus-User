package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.facade.UserFacade
import hs.kr.equus.user.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.user.infrastructure.kafka.dto.DeleteUserEventRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserWithdrawalService(
    private val deleteUserKafkaTemplate: KafkaTemplate<String, DeleteUserEventRequest>,
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute() {
        val user = userFacade.getCurrentUser()
        userRepository.deleteById(user.id)
        deleteUserKafkaTemplate.send(KafkaTopics.DELETE_USER, DeleteUserEventRequest(user.id))
    }
}
