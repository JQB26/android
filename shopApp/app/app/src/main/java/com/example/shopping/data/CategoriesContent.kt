package com.example.shopping.data

import com.example.shopping.models.CategoryModel
import java.util.ArrayList
import java.util.HashMap

object CategoriesContent {
    val CATEGORIES: MutableList<CategoryModel> = ArrayList()
    val CATEGORIES_MAP: MutableMap<String, CategoryModel> = HashMap()

    init {
        addCategory(createCategory(
            "1",
            "Boardgames",
            listOf("Social", "Family", "Friends"),
            "Board games are a type of tabletop game that involves playing with physical pieces on a flat surface or board. Board games typically have a set of rules and often involve strategy, chance, or both. They can be played by two or more players, and often involve players taking turns to move their pieces or roll dice to determine the outcome of their actions."
        ))
        addCategory(createCategory(
            "2",
            "Computer Games",
            listOf("PC", "Video"),
            "Computer games, also known as video games, are a type of digital game that is played on a computer or other electronic device. Computer games often involve interacting with a graphical user interface (GUI) or control system to manipulate virtual objects or characters in a simulated environment. Computer games can be played by one or more players, either locally or over the internet, and often involve a combination of skill, strategy, and chance. Computer games can be categorized into various genres, such as action, adventure, strategy, puzzle, and simulation, and are often developed using specialized software and game engines."
        ))
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