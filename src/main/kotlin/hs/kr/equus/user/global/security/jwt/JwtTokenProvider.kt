package hs.kr.equus.user.global.security.jwt

import hs.kr.equus.user.domain.refreshtoken.domain.RefreshToken
import hs.kr.equus.user.domain.refreshtoken.domain.repository.RefreshTokenRepository
import hs.kr.equus.user.domain.user.domain.UserRole
import hs.kr.equus.user.domain.user.domain.repository.UserRepository
import hs.kr.equus.user.global.exception.ExpiredTokenException
import hs.kr.equus.user.global.exception.InvalidTokenException
import hs.kr.equus.user.global.security.auth.AdminDetailsService
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
    private val userRepository: UserRepository,
    private val adminDetailsService: AdminDetailsService
) {
    companion object {
        private const val ACCESS_KEY = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }

    fun reIssue(refreshToken: String): TokenResponse {
        if (!isRefreshToken(refreshToken)) {
            throw InvalidTokenException
        }

        refreshTokenRepository.findByToken(refreshToken)
            ?.let { token ->
                val id = token.id
                val role = getRole(token.token)

                val tokenResponse = generateToken(id, role)
                token.update(tokenResponse.refreshToken, jwtProperties.refreshExp)
                return TokenResponse(tokenResponse.accessToken, tokenResponse.refreshToken)
            } ?: throw InvalidTokenException
    }

    fun generateToken(userId: String, role: String): TokenResponse {
        val accessToken = generateAccessToken(userId, role, ACCESS_KEY, jwtProperties.accessExp)
        val refreshToken = generateRefreshToken(role, REFRESH_KEY, jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(userId, refreshToken, jwtProperties.refreshExp)
        )
        return TokenResponse(accessToken, refreshToken)
    }

    private fun generateAccessToken(id: String, role: String, type: String, exp: Long): String =
        Jwts.builder()
            .setSubject(id)
            .setHeaderParam("typ", type)
            .claim("role", role)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()

    private fun generateRefreshToken(role: String, type: String, exp: Long): String =
        Jwts.builder()
            .setHeaderParam("typ", type)
            .claim("role", role)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()

    fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.also {
            if (it.startsWith(jwtProperties.prefix)) {
                return it.substring(jwtProperties.prefix.length)
            }
        }

    fun authentication(token: String): Authentication? {
        val body: Claims = getJws(token).body
        if (!isRefreshToken(token)) throw InvalidTokenException
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

    private fun isRefreshToken(token: String?): Boolean {
        return REFRESH_KEY == getJws(token!!).header["typ"].toString()
    }

    private fun getRole(token: String) = getJws(token).body["role"].toString()

    private fun getDetails(body: Claims): UserDetails {
        return if (UserRole.USER.toString() == body["role"].toString()) {
            authDetailsService.loadUserByUsername(body.subject)
        } else {
            adminDetailsService.loadUserByUsername(body.subject)
        }
    }
}
