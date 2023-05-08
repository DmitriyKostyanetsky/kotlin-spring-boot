package com.kostyanetskiy.orderservice.service

import com.kostyanetskiy.orderservice.domain.Order
import com.kostyanetskiy.orderservice.repository.OrderRepository
import com.kostyanetskiy.orderservice.util.OrderMessageConverter
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class OrderService(
    val orderRepository: OrderRepository,
    val jmsTemplate: JmsTemplate,
    @Value("\${artemis}")
    var queue: String,
    val orderMessageConverter: OrderMessageConverter
) {


    fun getOrder(id: Long): Order {
        return orderRepository.getReferenceById(id)
    }

    @Transactional
    fun createOrder(order: Order): Order {
        val createdOrder = orderRepository.save(order)
        jmsTemplate.convertAndSend(queue, createdOrder)
        return createdOrder
    }

    @Transactional
    fun updateOrder(id: Long): Order {
        val order = getOrder(id)
        return orderRepository.save(order)
    }

    @Transactional
    fun deleteOrder(id: Long) {
        val order = getOrder(id)
        orderRepository.delete(order)
    }

    fun getAllOrder(): List<Order> {
        return orderRepository.findAll()
    }
}
