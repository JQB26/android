package com.example.shopping.data

import com.example.shopping.models.CartItemModel
import com.example.shopping.models.ProductModel
import java.util.ArrayList
import java.util.HashMap

object CartContent {
    val CART: MutableList<CartItemModel> = ArrayList()
    val CART_MAP: MutableMap<String, CartItemModel> = HashMap()

//    init {
//        addProductToCart("1")
//        addProductToCart("1")
//        addProductToCart("2")
//    }

    fun addProductToCart(id: String) {
        val product: ProductModel? = ProductsContent.PRODUCTS_MAP[id]
        when (val quantity = CART_MAP[id]?.quantity)
        {
            null -> {
                val cartItem = product?.let {
                    CartItemModel(
                        id,
                        1,
                        it.price
                    )
                }
                if (cartItem != null) {
                    CART.add(cartItem)
                    CART_MAP[cartItem.id] = cartItem
                }
            }
            else -> {
                if (CART_MAP[id] != null) {
                    CART_MAP[id]?.quantity = CART_MAP[id]?.quantity?.plus(1)!!
                }
            }
        }
    }

    fun removeProductFromCart(id: String) {
        when (val quantity = CART_MAP[id]?.quantity)
        {
            1 -> {
                CART.remove(CART_MAP[id])
                CART_MAP.remove(id)
            }
            else -> {
                if (CART_MAP[id] != null) {
                    CART_MAP[id]?.quantity = CART_MAP[id]?.quantity?.plus(-1)!!
                }
            }
        }
    }
}