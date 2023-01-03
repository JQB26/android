package com.example.shopping.adapters

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.shopping.data.CartContent

import com.example.shopping.databinding.FragmentProductBinding
import com.example.shopping.models.ProductModel


class MyProductRecyclerViewAdapter(
    private val values: List<ProductModel>,
    private val context: Context,
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

            val toast = Toast.makeText(context, "${item.name}\nadded to the cart", Toast.LENGTH_SHORT)
            toast.show()
        }

        holder.removeFromCart.setOnClickListener {
            if (cart.CART_MAP[item.id] != null) {
                cart.removeProductFromCart(item.id)

                val toast = Toast.makeText(context, "${item.name}\nremoved from the cart", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast = Toast.makeText(context, "No such item in the cart", Toast.LENGTH_SHORT)
                toast.show()
            }

        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val price: TextView = binding.productPrice
        val name: TextView = binding.name
        val addToCart: Button = binding.addToCart
        val removeFromCart: Button = binding.removeFromCart


        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}