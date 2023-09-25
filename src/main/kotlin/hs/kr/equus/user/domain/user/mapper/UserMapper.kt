package hs.kr.equus.user.domain.user.mapper

import hs.kr.equus.user.domain.user.domain.UserJpaEntity
import hs.kr.equus.user.domain.user.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toDomain(user: UserJpaEntity) = user.run {
        User(
            id = id,
            phoneNumber = phoneNumber,
            name = name,
            isStudent = isStudent,
            receiptCode = receiptCode
        )
    }
}
