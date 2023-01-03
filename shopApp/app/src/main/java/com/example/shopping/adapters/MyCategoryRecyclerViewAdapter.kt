package com.example.shopping.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.shopping.data.ProductsContent
import com.example.shopping.databinding.FragmentCategoryBinding

import com.example.shopping.models.CategoryModel


class MyCategoryRecyclerViewAdapter(
    private val values: List<CategoryModel>,
    private val clickListener: (String) -> Intent
) : RecyclerView.Adapter<MyCategoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name

        val itemsInCategory = ProductsContent.PRODUCTS.filter { productModel ->
            productModel.category_id.toString() == item.id
        }.size

        holder.itemsInCategory.text = buildString {
        append("Items: ")
        append(itemsInCategory)
    }

        holder.itemView.setOnClickListener {
            clickListener(item.details)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val itemsInCategory = binding.itemsInCategory

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}