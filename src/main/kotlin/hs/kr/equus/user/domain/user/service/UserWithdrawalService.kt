package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.facade.UserFacade
import hs.kr.equus.user.infrastructure.kafka.producer.DeleteUserProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserWithdrawalService(
    private val deleteUserProducer: DeleteUserProducer,
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute() {
        val user = userFacade.getCurrentUser()
        userRepository.deleteById(user.id)
        deleteUserProducer.send(user.id!!)
    }
}
