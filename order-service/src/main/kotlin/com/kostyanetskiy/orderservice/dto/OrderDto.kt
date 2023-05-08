package com.kostyanetskiy.orderservice.dto

import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
class OrderDto(val id: Long?, val name: String?, val quantity: Int?)
