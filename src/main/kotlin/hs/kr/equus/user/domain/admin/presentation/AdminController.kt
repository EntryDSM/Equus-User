package hs.kr.equus.user.domain.admin.presentation

import hs.kr.equus.user.domain.admin.presentation.dto.request.AdminLoginRequest
import hs.kr.equus.user.domain.admin.service.AdminLoginService
import hs.kr.equus.user.domain.admin.service.TokenRefreshService
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminLoginService: AdminLoginService,
    private val tokenRefreshService: TokenRefreshService
) {
    @PostMapping("/auth")
    fun login(
        @RequestBody @Valid
        adminLoginRequest: AdminLoginRequest
    ): TokenResponse =
        adminLoginService.execute(adminLoginRequest)

    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse =
        tokenRefreshService.execute(refreshToken)
}
