package hs.kr.equus.user.global.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT")
            .allowedOrigins(
                "http://localhost:3000",
                "http://localhost:3001",
                "http://localhost:3002"
            )
    }
}
