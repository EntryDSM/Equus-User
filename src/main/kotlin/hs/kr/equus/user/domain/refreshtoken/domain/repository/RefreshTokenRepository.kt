package hs.kr.equus.user.domain.refreshtoken.domain.repository

import hs.kr.equus.user.domain.refreshtoken.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByToken(token: String): RefreshToken?
}
