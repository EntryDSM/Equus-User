package hs.kr.equus.user.domain.user.facade

import hs.kr.equus.user.domain.user.domain.UserJpaEntity
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): UserJpaEntity {
        val userId = SecurityContextHolder.getContext().authentication.name
        return userRepository.findById(UUID.fromString(userId)).orElseThrow { UserNotFoundException }
    }

    fun getUserByPhoneNumber(phoneNumber: String): UserJpaEntity =
        userRepository.findByPhoneNumber(phoneNumber) ?: throw UserNotFoundException
}
