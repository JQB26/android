package com.example.shopping

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val destination = intent.getStringExtra("destination")
        product_details.text = intent.getStringExtra("details")

        backButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                it.putExtra("destination", destination)
                startActivity(it)
            }
        }
    }

    companion object {
        fun newIntent(context: Context, destination: String): Intent {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("destination", destination)
            return intent
        }
    }
}