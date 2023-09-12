package hs.kr.equus.user.domain.user.facade

import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User? {
        val userId = SecurityContextHolder.getContext().authentication.name
        return userRepository.findById(UUID.fromString(userId)).orElseThrow { UserNotFoundException }
    }

    fun getUserByTelephoneNumber(telephoneNumber: String): User? {
        return userRepository.findByTelephoneNumber(telephoneNumber).orElseThrow { UserNotFoundException }
    }

    fun getUserByUserId(id: String): User? {
        return userRepository.findById(UUID.fromString(id)).orElseThrow{ UserNotFoundException }
    }
}
