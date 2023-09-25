package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.presentation.dto.response.UserResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryUserByUUIDService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun execute(userId: UUID): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException
        return UserResponse(
            id = user.id,
            phoneNumber = user.phoneNumber,
            name = user.name,
            isStudent = user.isStudent,
            receiptCode = user.receiptCode
        )
    }
}
