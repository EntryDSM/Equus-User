package hs.kr.equus.user.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.user.global.security.jwt.JwtProperties
import hs.kr.equus.user.global.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsUtils

@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf()
            .disable()
            .cors()
            .and()
            .formLogin()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .requestMatchers(CorsUtils::isCorsRequest)
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/user/verify/**")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/user/verify/info")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/user/auth")
            .permitAll()
            .anyRequest()
            .authenticated()

        http
            .apply(FilterConfig(jwtTokenProvider, jwtProperties, objectMapper))

        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}
