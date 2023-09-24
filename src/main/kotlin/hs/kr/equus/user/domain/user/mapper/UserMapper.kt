package hs.kr.equus.user.domain.user.mapper

import hs.kr.equus.user.domain.user.domain.UserJpaEntity
import hs.kr.equus.user.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toDomain(user: UserJpaEntity): User {
        return User(
            user.id,
            user.phoneNumber,
            user.name,
            user.isStudent,
            user.receiptCode
        )
    }
}
