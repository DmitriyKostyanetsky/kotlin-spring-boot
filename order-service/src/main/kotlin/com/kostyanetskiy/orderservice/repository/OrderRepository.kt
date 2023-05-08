package com.kostyanetskiy.orderservice.repository

import com.kostyanetskiy.orderservice.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>
