package com.example.shopping.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping.PaymentActivity
import com.example.shopping.ProductDetailsActivity
import com.example.shopping.R
import com.example.shopping.adapters.MyCartRecyclerViewAdapter
import com.example.shopping.data.CartContent
import kotlinx.android.synthetic.main.fragment_cart.view.*


class CartFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Set the adapter
        if (view.cart_list is RecyclerView) {
            with(view.cart_list) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    activity?.let { it ->
                        MyCartRecyclerViewAdapter(CartContent.CART, it) { it ->
                            val details: String = it
                            Intent(activity, ProductDetailsActivity::class.java).also {
                                it.putExtra("destination", "CartFragment")
                                it.putExtra("details", details)
                                startActivity(it)
                            }
                        }
                    }
            }
        }

        view.payButton.setOnClickListener {
            Intent(activity, PaymentActivity::class.java).also {
                startActivity(it)
            }
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}