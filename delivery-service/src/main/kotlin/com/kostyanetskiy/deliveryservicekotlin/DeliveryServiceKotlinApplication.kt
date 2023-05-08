package com.kostyanetskiy.deliveryservicekotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.kostyanetskiy.deliveryservicekotlin.util.OrderMessageConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.core.JmsTemplate

@EnableJms
@SpringBootApplication
class DeliveryServiceKotlinApplication

fun main(args: Array<String>) {
    runApplication<DeliveryServiceKotlinApplication>(*args)
}

@Bean
fun jmsTemplate(): JmsTemplate = JmsTemplate()

@Bean
fun objectMapper(): ObjectMapper = ObjectMapper()
