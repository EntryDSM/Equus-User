package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class ChangeReceiptCodeService(
    private val userRepository: UserRepository
) {
    fun execute(userId: UUID, receiptCode: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException
        user.changeReceiptCode(receiptCode)
    }
}
