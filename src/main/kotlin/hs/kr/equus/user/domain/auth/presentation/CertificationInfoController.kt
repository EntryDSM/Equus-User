package hs.kr.equus.user.domain.auth.presentation

import hs.kr.equus.user.domain.auth.presentation.dto.request.CheckCertificationInfoRequest
import hs.kr.equus.user.domain.auth.service.CheckCertificationInfoService
import hs.kr.equus.user.domain.auth.service.SendCertificationNumberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/certification")
class CertificationInfoController(
    private val sendCertificationNumberService: SendCertificationNumberService,
    private val checkCertificationInfoService: CheckCertificationInfoService
) {
    @PostMapping("/send")
    fun sendCertificationMessage(@RequestParam("phone_number") phoneNumber: String) =
        sendCertificationNumberService.execute(phoneNumber)

    @PutMapping("/check")
    fun checkCertificationNumber(
        @RequestBody
        checkCertificationInfoRequest: CheckCertificationInfoRequest
    ) = checkCertificationInfoService.execute(checkCertificationInfoRequest)
}
