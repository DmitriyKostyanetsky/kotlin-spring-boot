package com.kostyanetskiy.orderservice.config

import jakarta.jms.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.listener.DefaultMessageListenerContainer


@Configuration
class ArtemisConfig(val connectionFactory: ConnectionFactory) {

    @Value("\${artemis}")
    var queue: String? = null

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory? {
        val jmsListenerContainerFactory = DefaultJmsListenerContainerFactory()
        jmsListenerContainerFactory.setConnectionFactory(this.connectionFactory)
        jmsListenerContainerFactory.setConcurrency("2-4")
        return jmsListenerContainerFactory
    }

//    @Bean
//    fun messageListener(): DefaultMessageListenerContainer? {
//        val container = DefaultMessageListenerContainer()
//        container.connectionFactory = this.connectionFactory
//        container.destinationName = queue
////        container.messageListener = Consumer()
//        return container
//    }
}
