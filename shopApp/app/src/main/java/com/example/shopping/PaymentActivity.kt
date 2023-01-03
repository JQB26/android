package com.example.shopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.shopping.data.CartContent
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val totalPrice = calculateTotalPrice()
        val totalPriceView: TextView = findViewById(R.id.totalPrice)
        totalPriceView.text = totalPrice.toString().plus("$")

        acceptPayment.setOnClickListener {
            if (allFieldsAreFilled()) {
                // TODO: send payment request
                val toast = Toast.makeText(this, "Payment was sent", Toast.LENGTH_SHORT)
                toast.show()

                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                val toast = Toast.makeText(this, "All of the fields has to be filled", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun calculateTotalPrice(): Double {
        // TODO: check if calculates correctly
        var totalPrice = 0.0
        CartContent.CART.forEach { cartItemModel ->
            val sum: Double = cartItemModel.unitPrice * cartItemModel.quantity
            totalPrice += sum
        }
        totalPrice -= totalPrice.mod(0.01)
        return totalPrice
    }

    private fun allFieldsAreFilled(): Boolean {
        // TODO: fix, not working
        if (textInputName.isEmpty()) {
            textInputName.boxStrokeErrorColor
            return false
        }

        return true
    }
}