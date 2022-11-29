package com.example.shopping

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.shopping.databinding.FragmentProductBinding
import com.example.shopping.product.ProductsContent


class MyProductRecyclerViewAdapter(
    private val values: List<ProductsContent.Product>
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.price.text = item.price.toString().plus("$")
        holder.name.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val price: TextView = binding.productPrice
        val name: TextView = binding.name

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}