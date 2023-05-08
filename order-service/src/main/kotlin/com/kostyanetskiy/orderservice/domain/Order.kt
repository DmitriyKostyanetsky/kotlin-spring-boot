package com.kostyanetskiy.orderservice.domain

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor

@Entity
@Table(name = "order_1")
@AllArgsConstructor
@NoArgsConstructor
open class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null
    open var name: String? = null
    open var quantity: Int? = null

    override fun toString(): String {
        return "Order(id=$id, name=$name, quantity=$quantity)"
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Order
//
//        if (id != other.id) return false
//        if (name != other.name) return false
//        if (quantity != other.quantity) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = id?.hashCode() ?: 0
//        result = 31 * result + (name?.hashCode() ?: 0)
//        result = 31 * result + (quantity ?: 0)
//        return result
//    }


}
