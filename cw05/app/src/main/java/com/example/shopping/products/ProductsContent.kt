package com.example.shopping.products

import java.util.ArrayList
import java.util.HashMap

object ProductsContent {
    val PRODUCTS: MutableList<Product> = ArrayList()
    private val PRODUCTS_MAP: MutableMap<String, Product> = HashMap()


    init {
        // add init products
        addProduct(createProduct(
            1,
            "Brass Birmingham",
            80,
            "Best board game ever"
        ))
        addProduct(createProduct(
            2,
            "Chess",
            40,
            "Most iconic game ever"
        ))
    }

    private fun addProduct(product: Product) {
        PRODUCTS.add(product)
        PRODUCTS_MAP[product.id] = product
    }

    private fun createProduct(id: Int, name: String, price: Int, details: String): Product {
        val processedDetails: String = makeDetails(name, details)
        return Product(id.toString(), name, price, processedDetails)
    }

    private fun makeDetails(name: String, detailsText: String): String {
        val builder = StringBuilder()
        builder.append("Details about ${name}: ")
        builder.append("\n${detailsText}")
        return builder.toString()
    }


    data class Product(val id: String, val name: String, val price: Int, val details: String) {
        override fun toString(): String = name
    }
}