package com.example.shopping.cart

import java.util.HashMap

object CartContent {
    val CART: MutableMap<String, Int> = HashMap()

    fun addProductToCart(id: String) {
        when (val count = CART[id])
        {
            null -> CART[id] = 1
            else -> CART[id] = count + 1
        }
    }
}