package com.kostyanetskiy.deliveryservicekotlin.service

import com.kostyanetskiy.deliveryservicekotlin.domain.Delivery
import com.kostyanetskiy.deliveryservicekotlin.dto.OrderDto
import com.kostyanetskiy.deliveryservicekotlin.util.OrderMessageConverter
import jakarta.jms.TextMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service

@Service
class DeliveryService(val orderMessageConverter: OrderMessageConverter) {

    @JmsListener(destination = "\${spring.artemis.embedded.queues}", id = "DeliveryService")
    fun receiveOrder(textMessage: TextMessage) {
        val order: OrderDto = orderMessageConverter.fromMessage(textMessage)
        val delivery = Delivery(orderId = order.id, name = order.name, quantity = order.quantity)
        println("delivery service started delivery $delivery")
    }
}
