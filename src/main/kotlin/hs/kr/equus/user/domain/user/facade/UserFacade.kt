package hs.kr.equus.user.domain.user.facade

import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User? {
        val telephoneNumber = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByTelephoneNumber(telephoneNumber).orElseThrow { UserNotFoundException }
    }

    fun getUserByTelephoneNumber(telephoneNumber: String): User? {
        return userRepository.findByTelephoneNumber(telephoneNumber).orElseThrow { UserNotFoundException }
    }
}
