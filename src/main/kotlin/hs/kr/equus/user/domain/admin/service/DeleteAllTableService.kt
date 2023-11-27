package hs.kr.equus.user.domain.admin.service

import hs.kr.equus.user.infrastructure.kafka.producer.DeleteAllTableProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteAllTableService(
    private val deleteAllTableProducer: DeleteAllTableProducer
) {

    @Transactional
    fun execute(){
        deleteAllTableProducer.send()
    }
}
