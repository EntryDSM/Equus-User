package hs.kr.equus.user.global.security.jwt

import hs.kr.equus.user.domain.refreshtoken.domain.RefreshToken
import hs.kr.equus.user.domain.refreshtoken.domain.repository.RefreshTokenRepository
import hs.kr.equus.user.domain.user.domain.Role
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.domain.user.exception.UserNotFoundException
import hs.kr.equus.user.global.exception.ExpiredTokenException
import hs.kr.equus.user.global.exception.InvalidTokenException
import hs.kr.equus.user.global.security.auth.AuthDetailsService
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository
) {
    companion object {
        private const val ACCESS_KEY = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }

    fun generateToken(phoneNumber: String, role: String): TokenResponse {
        val userId = getUserUUID(phoneNumber).toString()
        val accessToken = generateToken(userId, role, ACCESS_KEY, jwtProperties.accessExp)
        val refreshToken = generateToken(userId, role, REFRESH_KEY, jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(userId, refreshToken, jwtProperties.refreshExp)
        )
        return TokenResponse(accessToken, refreshToken)
    }

    private fun generateToken(id: String, role: String, type: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(id)
            .setHeaderParam("typ", type)
            .claim("role", role)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader(jwtProperties.header)
        return if (
            bearer != null && bearer.startsWith(jwtProperties.prefix) && bearer.length > jwtProperties.prefix.length + 1
        ) {
            bearer.substring(jwtProperties.prefix.length + 1)
        } else {
            null
        }
    }

    fun authentication(token: String): Authentication? {
        val body: Claims = getJws(token).body
        if (!isNotRefreshToken(token)) throw InvalidTokenException
        val userDetails: UserDetails = getDetails(body)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getJws(token: String): Jws<Claims> {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException
        } catch (e: Exception) {
            throw InvalidTokenException
        }
    }

    fun isNotRefreshToken(token: String?): Boolean {
        return REFRESH_KEY != getJws(token!!).header["typ"].toString()
    }

    private fun getDetails(body: Claims): UserDetails {
        return if (Role.USER.toString() == body["role"].toString()) {
            authDetailsService.loadUserByUsername(body.subject)
        } else {
            // 어드민 구현 후 추가 예정
            authDetailsService.loadUserByUsername(body.subject)
        }
    }

    private fun getUserUUID(phoneNumber: String) =
        userRepository.findByTelephoneNumber(phoneNumber).orElseThrow{ UserNotFoundException }.id
}
