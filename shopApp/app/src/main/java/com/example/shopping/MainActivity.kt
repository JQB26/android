package com.example.shopping

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shopping.databinding.ActivityMainBinding
import com.example.shopping.fragments.CartFragment
import com.example.shopping.fragments.CategoryFragment
import com.example.shopping.fragments.ProductFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getStringExtra("destination"))
        {
            "ProductFragment" -> replaceFragment(ProductFragment())
            "CategoryFragment" -> replaceFragment(CategoryFragment())
            "CartFragment" -> replaceFragment(CartFragment())
            else -> replaceFragment(ProductFragment())
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(ProductFragment())
                R.id.category -> replaceFragment(CategoryFragment())
                R.id.cart -> replaceFragment(CartFragment())
                else -> {}
            }
            true
        }

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("userName", "")

        val textWelcome: TextView = findViewById(R.id.textWelcome)
        val firstName = userName?.split(" ")?.get(0)
        textWelcome.text = textWelcome.text.toString().plus(" $firstName")
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        fun newIntent(context: Context, destination: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("destination", destination)
            return intent
        }
    }
}

