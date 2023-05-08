package com.kostyanetskiy.orderservice.controller

import com.kostyanetskiy.orderservice.domain.Order
import com.kostyanetskiy.orderservice.service.OrderService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
class OrderController(val orderService: OrderService) {

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long) = orderService.getOrder(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody order: Order) = orderService.createOrder(order)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun createOrder(@PathVariable id: Long) = orderService.updateOrder(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable id: Long) = orderService.deleteOrder(id)

    @GetMapping
    fun getAllOrder() = orderService.getAllOrder()

}
