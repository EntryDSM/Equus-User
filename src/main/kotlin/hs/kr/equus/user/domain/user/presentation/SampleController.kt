package hs.kr.equus.user.domain.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/samples")
@RestController
class SampleController {
    @GetMapping("{sampleId}")
    fun getSampleById(
        @PathVariable sampleId: String
    ): SampleResponse =
        SampleResponse(sampleId, "sample-$sampleId")
}

data class SampleResponse(
    val sampleId: String,
    val name: String
)
