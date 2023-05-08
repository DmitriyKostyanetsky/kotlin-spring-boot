package com.kostyanetskiy.deliveryservicekotlin.dto

import lombok.Builder
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
@Builder
class OrderDto(val id: Long, val name: String, val quantity: Int)
