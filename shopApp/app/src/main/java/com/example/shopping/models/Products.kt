package com.example.shopping.models

data class ProductModel(
    val id: String,
    val name: String,
    val price: Double,
    val details: String,
    val category: CategoryModel
)