package hs.kr.equus.user.domain.user.domain.repository

import hs.kr.equus.user.domain.user.domain.UserCache
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserCacheRepository : CrudRepository<UserCache, UUID> {
}
