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
import com.example.shopping.ProductDetailsActivity
import com.example.shopping.R
import com.example.shopping.adapters.MyCategoryRecyclerViewAdapter
import com.example.shopping.data.CategoriesContent


class CategoryFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)

        println("KROWA2 ${CategoriesContent.CATEGORIES}")

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    activity?.let {
                        MyCategoryRecyclerViewAdapter(CategoriesContent.CATEGORIES) { it ->
                            val details: String = it
                            Intent(activity, ProductDetailsActivity::class.java).also {
                                it.putExtra("destination", "CategoryFragment")
                                it.putExtra("details", details)
                                startActivity(it)
                            }
                        }
                    }
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}