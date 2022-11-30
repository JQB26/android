package com.example.shopping

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.shopping.cart.CartContent

import com.example.shopping.databinding.FragmentProductBinding
import com.example.shopping.products.ProductsContent


class MyProductRecyclerViewAdapter(
    private val values: List<ProductsContent.Product>,
    private val clickListener: (String) -> Intent
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>() {

    private val cart: CartContent = CartContent

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

        holder.itemView.setOnClickListener {
            clickListener(item.details)
        }

        holder.addToCart.setOnClickListener {
            cart.addProductToCart(item.id)

            // TODO: remove println when cart activity/view is added
            println(cart.CART.map{ "${it.key}: ${it.value}" }.joinToString(", "))
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val price: TextView = binding.productPrice
        val name: TextView = binding.name
        val addToCart: Button = binding.addToCart

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}