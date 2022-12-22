package com.example.shopping.data

import android.os.AsyncTask
import com.example.shopping.FetchDataTask
import com.example.shopping.models.CategoryModel
import com.example.shopping.models.ProductModel
import com.google.gson.Gson
import java.net.URL
import java.util.ArrayList
import java.util.HashMap

object CategoriesContent {
    val CATEGORIES: MutableList<CategoryModel> = ArrayList()
    val CATEGORIES_MAP: MutableMap<String, CategoryModel> = HashMap()

    private val gson = Gson()

    private val url = URL("http://10.0.2.2:80/categories")

    private val task = FetchDataTask()
    private val response: AsyncTask<URL, Void, String> = task.execute(url)

    init {
        val data = response.get()
        val categories: List<CategoryModel> = gson.fromJson(data, Array<CategoryModel>::class.java).toList()

        categories.forEach { category ->
            addCategory(category)
        }
    }

    private fun addCategory(category: CategoryModel) {
        CATEGORIES.add(category)
        CATEGORIES_MAP[category.id] = category
    }

    private fun createCategory(
        id: String, name: String, keywords: List<String>, description: String
    ): CategoryModel {
        return CategoryModel(id, name, keywords, description)
    }
}