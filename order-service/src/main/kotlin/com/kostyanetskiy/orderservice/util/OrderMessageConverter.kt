package com.kostyanetskiy.orderservice.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.kostyanetskiy.orderservice.domain.Order
import jakarta.jms.Message
import jakarta.jms.Session
import jakarta.jms.TextMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.stereotype.Component

@Component
class OrderMessageConverter(private val objectMapper: ObjectMapper): MessageConverter {

    private val log: Logger = LoggerFactory.getLogger(OrderMessageConverter::class.java)

    override fun toMessage(`object`: Any, session: Session): Message {
        val order = `object` as Order
        var payLoad: String? = null
        try {
            payLoad = objectMapper.writeValueAsString(order)
            log.info("outbound json $payLoad")
        } catch (e: JsonProcessingException) {
            log.error("error converting from order ${e.message}")
        }

        val message = session.createTextMessage()
        message.text = payLoad

        return message
    }

    override fun fromMessage(message: Message): Order {
        val textMessage = message as TextMessage
        val payLoad = textMessage.text
        log.info("inbound json $payLoad")

        var order: Order? = null
        try {
            order = objectMapper.readValue(payLoad, Order::class.java)
        } catch (e: JsonMappingException) {
            log.error("error converting to order $payLoad")
        }

        return order!!
    }
}
