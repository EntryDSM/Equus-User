package hs.kr.equus.user.domain.user.facade

import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.global.exception.InvalidTokenException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.util.*

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val userId = SecurityContextHolder.getContext().authentication.name
        try {
            return userRepository.findById(UUID.fromString(userId)).orElseThrow { UserNotFoundException }
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException
        }

    }

    fun getUserByPhoneNumber(phoneNumber: String): User =
        userRepository.findByPhoneNumber(phoneNumber) ?: throw UserNotFoundException
}
