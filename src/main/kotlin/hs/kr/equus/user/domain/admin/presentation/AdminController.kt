package hs.kr.equus.user.domain.admin.presentation

import hs.kr.equus.user.domain.admin.presentation.dto.request.AdminLoginRequest
import hs.kr.equus.user.domain.admin.service.AdminLoginService
import hs.kr.equus.user.domain.admin.service.AdminTokenRefreshService
import hs.kr.equus.user.domain.admin.service.DeleteAllTableService
import hs.kr.equus.user.domain.admin.service.QueryAdminByUUIDService
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminLoginService: AdminLoginService,
    private val adminTokenRefreshService: AdminTokenRefreshService,
    private val deleteAllTableService: DeleteAllTableService,
    private val queryAdminByUUIDService: QueryAdminByUUIDService
) {
    @PostMapping("/auth")
    fun login(
        @RequestBody @Valid
        adminLoginRequest: AdminLoginRequest
    ): TokenResponse =
        adminLoginService.execute(adminLoginRequest)

    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse =
        adminTokenRefreshService.execute(refreshToken)

    @DeleteMapping("/auth")
    fun deleteAllTable() = deleteAllTableService.execute()

    @GetMapping("/{adminId}")
    fun findAdminById(@PathVariable adminId: UUID) =
        queryAdminByUUIDService.execute(adminId)
}
