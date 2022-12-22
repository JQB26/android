package com.example.shopping.models

data class ProductModel(
    val id: String,
    val name: String,
    val price: Float,
    val category: CategoryModel,
    val details: String
)