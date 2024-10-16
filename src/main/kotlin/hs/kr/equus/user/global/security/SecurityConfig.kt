package hs.kr.equus.user.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
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
            .antMatchers("/")
            .permitAll()
            .antMatchers(HttpMethod.PATCH, "/user/password")
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/user")
            .permitAll()
            .antMatchers("/user/verify/popup")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/user/verify/info")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/user/auth")
            .permitAll()
            .antMatchers(HttpMethod.PUT, "/user/auth")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/admin/auth")
            .permitAll()
            .antMatchers(HttpMethod.DELETE, "/admin/auth")
            .hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/user")
            .hasRole("ROOT")
            .antMatchers(HttpMethod.GET, "/admin/")
            .hasRole("ROOT")
            .anyRequest()
            .authenticated()

        http
            .apply(FilterConfig(objectMapper))

        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}
