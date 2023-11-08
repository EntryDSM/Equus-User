package hs.kr.equus.user.domain.auth.presentation

import hs.kr.equus.user.domain.auth.presentation.dto.request.PassPopupRequest
import hs.kr.equus.user.domain.auth.presentation.dto.resopnse.QueryPassInfoResponse
import hs.kr.equus.user.domain.auth.service.PassPopupService
import hs.kr.equus.user.domain.auth.service.QueryPassInfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user/verify")
class PassInfoController(
    private val passPopupService: PassPopupService,
    private val queryPassInfoService: QueryPassInfoService
) {
    @GetMapping("/info")
    fun getPassInfo(@RequestParam("mdl_tkn") token: String): QueryPassInfoResponse = queryPassInfoService.execute(token)

    @GetMapping("/popup")
    fun popupPass(@RequestBody request: @Valid PassPopupRequest): String = passPopupService.execute(request)
}
