package com.example.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shopping.databinding.ActivityMainBinding
import com.example.shopping.fragments.CartFragment
import com.example.shopping.fragments.ProductFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (val destination = intent.getStringExtra("destination"))
        {
            "ProductFragment" -> replaceFragment(ProductFragment())
            "CartFragment" -> replaceFragment(CartFragment())
            else -> replaceFragment(ProductFragment())
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(ProductFragment())
                R.id.cart -> replaceFragment(CartFragment())
                else -> {}
            }
            true
        }

        val user = intent.getStringExtra("user")

        if (user != null) {
            val textWelcome: TextView = findViewById(R.id.textWelcome)
            val userName = user.split(" ")[0]
            textWelcome.text = textWelcome.text.toString().plus(" $userName")
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}