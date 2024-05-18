package hs.kr.equus.user.infrastructure.kafka.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.*

@Configuration
class KafkaProducerConfig(
    private val kafkaProperty: KafkaProperty
) {

    @Bean
    fun deleteAllTableProducerFactory(): DefaultKafkaProducerFactory<String, Unit> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun deleteAllTableKafkaTemplate(): KafkaTemplate<String, Unit> {
        return KafkaTemplate(deleteAllTableProducerFactory())
    }

    @Bean
    fun deleteUserProducerFactory(): DefaultKafkaProducerFactory<String, UUID> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun deleteUserKafkaTemplate(): KafkaTemplate<String, UUID> {
        return KafkaTemplate(deleteUserProducerFactory())
    }

    private fun producerConfig(): Map<String, Any> {
        return mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.serverAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
            "security.protocol" to "SASL_SSL",
            "sasl.mechanism" to "PLAIN",
            "sasl.jaas.config" to
                "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                "username=\"${kafkaProperty.confluentApiKey}\" " +
                "password=\"${kafkaProperty.confluentApiSecret}\";"
        )
    }
}
