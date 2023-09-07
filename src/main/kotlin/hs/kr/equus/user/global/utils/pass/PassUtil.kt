package hs.kr.equus.user.global.utils.pass

import hs.kr.equus.user.domain.auth.exception.InvalidOkCertConnectException
import kcb.module.v3.OkCert
import kcb.module.v3.exception.OkCertException
import kcb.org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PassUtil {

    companion object {

        private val okCert = OkCert()

        private val TARGET = "PROD"

        private val SVC_NAME = "IDS_HS_POPUP_RESULT"

        private val MODEL_TOKEN = "MDL_TKN"
    }

    @Value("{pass.cp-cd}")
    private lateinit var CP_CD: String

    @Value("{pass.license}")
    private lateinit var LICENSE: String

    fun getResponseJson(token: String?): JSONObject? {
        val reqJson = JSONObject()
        reqJson.put(MODEL_TOKEN, token)
        val reqStr = reqJson.toString()
        var resultStr: String? = null
        resultStr = try {
            okCert.callOkCert(TARGET, CP_CD, SVC_NAME, LICENSE, reqStr)
        } catch (e: OkCertException) {
            throw InvalidOkCertConnectException
        }
        assert(resultStr != null)
        return JSONObject(resultStr)
    }
}