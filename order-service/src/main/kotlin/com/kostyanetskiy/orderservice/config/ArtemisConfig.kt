package com.kostyanetskiy.orderservice.config

import com.kostyanetskiy.orderservice.service.Consumer
import jakarta.jms.ConnectionFactory
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.listener.DefaultMessageListenerContainer


@Configuration
class ArtemisConfig(val connectionFactory: ConnectionFactory) {

    @Value("\${artemis}")
    var queue: String? = null

    @Bean
    fun messageListener(): DefaultMessageListenerContainer? {
        val container = DefaultMessageListenerContainer()
        container.connectionFactory = this.connectionFactory
        container.destinationName = queue
        container.messageListener = Consumer()
        return container
    }
}
