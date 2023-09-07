package hs.kr.equus.user.domain.auth.service

import hs.kr.equus.user.domain.auth.entity.PassInfo
import hs.kr.equus.user.domain.auth.entity.repository.PassInfoRepository
import hs.kr.equus.user.domain.auth.exception.InvalidPassException
import hs.kr.equus.user.domain.auth.presentation.dto.resopnse.QueryPassInfoResponse
import hs.kr.equus.user.global.utils.pass.PassUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryPassInfoService(
    private val passInfoRepository: PassInfoRepository,
    private val passUtil: PassUtil
) {

    companion object {

        private val RESULT_CODE = "RSLT_CD"

        private val RESULT_NAME = "RSLT_NAME"

        private val TEL_NO = "TEL_NO"

        private val RESULT_CODE_OK = "B000"
    }

    @Value("\${pass.exp}")
    private var EXP: Long = 0L

    @Transactional
    fun execute(token: String?): QueryPassInfoResponse {
        val resJson = passUtil.getResponseJson(token)
        val resultCode = resJson!!.getString(RESULT_CODE)
        if (RESULT_CODE_OK != resultCode) {
            throw InvalidPassException
        }
        val name = resJson.getString(RESULT_NAME)
        val phoneNumber = resJson.getString(TEL_NO)
        val passInfo: PassInfo = PassInfo(name, phoneNumber, EXP)
        passInfoRepository.save(passInfo)
        return QueryPassInfoResponse(phoneNumber, name)
    }
}
