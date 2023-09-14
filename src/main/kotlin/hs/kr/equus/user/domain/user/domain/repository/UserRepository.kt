package hs.kr.equus.user.domain.user.domain.repository

import hs.kr.equus.user.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByPhoneNumber(telephoneNumber: String): Optional<User>

    fun existsByPhoneNumber(telephoneNumber: String): Boolean
}
