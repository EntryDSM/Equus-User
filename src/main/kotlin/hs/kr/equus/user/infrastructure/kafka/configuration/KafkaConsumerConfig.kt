package hs.kr.equus.user.infrastructure.kafka.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    private val kafkaProperty: KafkaProperty
) {
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()

        factory.setConcurrency(2)
        factory.consumerFactory = DefaultKafkaConsumerFactory(consumerFactoryConfig())
        factory.containerProperties.pollTimeout = 500
        return factory
    }

    private fun consumerFactoryConfig(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperty.serverAddress
        props[ConsumerConfig.ISOLATION_LEVEL_CONFIG] = "read_committed"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = "false"
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "latest"
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG] = 5000
        return props
    }
}
