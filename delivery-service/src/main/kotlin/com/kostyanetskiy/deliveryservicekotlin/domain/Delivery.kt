package com.kostyanetskiy.deliveryservicekotlin.domain

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "delivery")
@NoArgsConstructor
@AllArgsConstructor
open class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null
    open var name: String? = null
    open var quantity: Int? = null
    open var orderId: Long? = null

    constructor(name: String?, quantity: Int?, orderId: Long?) {
        this.name = name
        this.quantity = quantity
        this.orderId = orderId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Delivery

        if (id != other.id) return false
        if (name != other.name) return false
        if (quantity != other.quantity) return false
        if (orderId != other.orderId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (quantity ?: 0)
        result = 31 * result + (orderId?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Delivery(id=$id, name=$name, quantity=$quantity, orderId=$orderId)"
    }


}
