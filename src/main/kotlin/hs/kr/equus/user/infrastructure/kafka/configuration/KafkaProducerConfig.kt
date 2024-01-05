package hs.kr.equus.user.infrastructure.kafka.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer

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
    fun deleteUserProducerFactory(): DefaultKafkaProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun deleteUserKafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(deleteUserProducerFactory())
    }

    private fun producerConfig(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap<String, Any>()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperty.serverAddress
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }
}
