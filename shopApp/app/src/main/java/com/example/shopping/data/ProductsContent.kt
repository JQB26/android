package com.example.shopping.data

import android.os.AsyncTask
import com.example.shopping.FetchDataTask
import com.example.shopping.models.ProductModel
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList
import java.util.HashMap

object ProductsContent {
    val PRODUCTS: MutableList<ProductModel> = ArrayList()
    private val PRODUCTS_MAP: MutableMap<String, ProductModel> = HashMap()

    private val gson = Gson()
    
    private val url = URL("http://10.0.2.2:80/products")

    private val task = FetchDataTask()
    private val response: AsyncTask<URL, Void, String> = task.execute(url)

    init {
        val data = response.get()
        val products: List<ProductModel> = gson.fromJson(data, Array<ProductModel>::class.java).toList()

        products.forEach { product ->
            addProduct(product)
        }
    }

    private fun addProduct(product: ProductModel) {
        PRODUCTS.add(product)
        PRODUCTS_MAP[product.id] = product
    }

    private fun createProduct(
        id: String, name: String, price: Double, details: String
    ): ProductModel {
        val processedDetails: String = makeDetails(name, details)
        return ProductModel(id, name, price, processedDetails)
    }

    private fun makeDetails(name: String, detailsText: String): String {
        val builder = StringBuilder()
        builder.append("Details about ${name}: ")
        builder.append("\n${detailsText}")
        return builder.toString()
    }
}