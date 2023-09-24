package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.domain.user.mapper.UserMapper
import hs.kr.equus.user.domain.user.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryUserByUUID(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    @Transactional(readOnly = true)
    fun execute(userId: UUID): User {
        val userJpaEntity = userRepository.findById(userId).orElseThrow { UserNotFoundException }
        return userMapper.toDomain(userJpaEntity)
    }
}
