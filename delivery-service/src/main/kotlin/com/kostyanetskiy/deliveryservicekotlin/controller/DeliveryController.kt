package com.kostyanetskiy.deliveryservicekotlin.controller

import com.kostyanetskiy.deliveryservicekotlin.service.DeliveryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/delivery")
class DeliveryController(val deliveryService: DeliveryService) {

    @GetMapping
    fun getMessages() {
        deliveryService.checkMails()
    }

}
