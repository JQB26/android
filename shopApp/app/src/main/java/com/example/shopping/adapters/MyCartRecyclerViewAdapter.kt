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

import com.example.shopping.databinding.FragmentCartItemBinding
import com.example.shopping.data.ProductsContent
import com.example.shopping.models.CartItemModel
import com.example.shopping.models.ProductModel


class MyCartRecyclerViewAdapter(
    private val values: List<CartItemModel>,
    private val context: Context,
    private val clickListener: (String) -> Intent
) : RecyclerView.Adapter<MyCartRecyclerViewAdapter.ViewHolder>() {

    private val cart: CartContent = CartContent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val product: ProductModel? = ProductsContent.PRODUCTS_MAP[item.id]
        if (product != null) {
            holder.name.text = product.name
            holder.productPrice.text = product.price.toString().plus("$")
        }
        holder.quantity.text = item.quantity.toString()

        holder.itemView.setOnClickListener {
            if (product != null) {
                clickListener(product.details)
            }
        }

        holder.removeFromCart.setOnClickListener {
            cart.removeProductFromCart(item.id)

            val toast = Toast.makeText(context, "${product?.name}\nremoved from the cart", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val quantity: TextView = binding.quantity
        val productPrice: TextView = binding.productPrice
        val removeFromCart: Button = binding.removeFromCart

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}