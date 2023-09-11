package hs.kr.equus.user.domain.user.domain.repository

import hs.kr.equus.user.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByTelephoneNumber(telephoneNumber: String): Optional<User>

    fun existsByTelephoneNumber(telephoneNumber: String): Boolean
}
