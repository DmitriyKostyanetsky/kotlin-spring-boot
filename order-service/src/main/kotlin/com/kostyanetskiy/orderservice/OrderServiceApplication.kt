package com.kostyanetskiy.orderservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.kostyanetskiy.orderservice.util.OrderMessageConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.core.JmsTemplate

@SpringBootApplication
@EnableJms
class OrderServiceApplication

fun main(args: Array<String>) {
    runApplication<OrderServiceApplication>(*args)
}

@Bean
fun jmsTemplate(): JmsTemplate = JmsTemplate()

@Bean
fun objectMapper(): ObjectMapper = ObjectMapper()
