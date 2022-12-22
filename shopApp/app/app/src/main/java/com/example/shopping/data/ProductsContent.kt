package com.example.shopping.data

import com.example.shopping.models.CategoryModel
import com.example.shopping.models.ProductModel
import java.util.ArrayList
import java.util.HashMap

object ProductsContent {
    val PRODUCTS: MutableList<ProductModel> = ArrayList()
    private val PRODUCTS_MAP: MutableMap<String, ProductModel> = HashMap()

    private val categories = CategoriesContent

    init {
        // add init products
        categories.CATEGORIES_MAP["1"]?.let {
            createProduct(
                "1",
                "Brass Birmingham",
                80.0,
                it,
                "Best board game ever"
            )
        }?.let {
            addProduct(
                it
            )
        }
        categories.CATEGORIES_MAP["1"]?.let {
            createProduct(
                "2",
                "Chess",
                40.0,
                it,
                "Most iconic game ever"
            )
        }?.let {
            addProduct(
                it
            )
        }
        categories.CATEGORIES_MAP["2"]?.let {
            createProduct(
                "3",
                "Civilization 6",
                20.60,
                it,
                "Best strategic video game"
            )
        }?.let {
            addProduct(
                it
            )
        }
    }

    private fun addProduct(product: ProductModel) {
        PRODUCTS.add(product)
        PRODUCTS_MAP[product.id] = product
    }

    private fun createProduct(
        id: String, name: String, price: Float, category: CategoryModel, details: String
    ): ProductModel {
        val processedDetails: String = makeDetails(name, details)
        return ProductModel(id, name, price, category, processedDetails)
    }

    private fun makeDetails(name: String, detailsText: String): String {
        val builder = StringBuilder()
        builder.append("Details about ${name}: ")
        builder.append("\n${detailsText}")
        return builder.toString()
    }
}