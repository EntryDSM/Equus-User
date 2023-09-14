package hs.kr.equus.user.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.user.global.error.GlobalExceptionFilter
import hs.kr.equus.user.global.security.jwt.JwtFilter
import hs.kr.equus.user.global.security.jwt.JwtProperties
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class FilterConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper,
    private val jwtProperties: JwtProperties
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            JwtFilter(jwtProperties, jwtTokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
        builder.addFilterBefore(GlobalExceptionFilter(), JwtFilter::class.java)
    }
}
