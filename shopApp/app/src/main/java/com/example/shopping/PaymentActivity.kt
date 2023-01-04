package com.example.shopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.shopping.data.CartContent
import kotlinx.android.synthetic.main.activity_payment.*
import java.net.HttpURLConnection
import java.net.URL

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val totalPrice = String.format("%.2f", calculateTotalPrice())
        val totalPriceView: TextView = findViewById(R.id.totalPrice)
        totalPriceView.text = totalPrice.plus("$")

        acceptPayment.setOnClickListener {
            if (allFieldsAreFilled()) {

                sendPaymentData(URL("http://10.0.2.2:80/payment"))

                val toast = Toast.makeText(this, "Payment was sent", Toast.LENGTH_SHORT)
                toast.show()

                CartContent.CART.clear()
                CartContent.CART_MAP.clear()

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
        var totalPrice = 0.0
        CartContent.CART.forEach { cartItemModel ->
            val sum: Double = cartItemModel.unitPrice * cartItemModel.quantity
            totalPrice += sum
        }
        return totalPrice
    }

    private fun allFieldsAreFilled(): Boolean {
        val requiredFieldList = listOf(
            textInputName,
            textInputSurname,
            textInputCardNumber,
            textInputCVV,
        )

        var requiredFieldsStatus = true

        for (requiredField in requiredFieldList) {
            if (requiredField.editText!!.text.isEmpty()) {
                requiredField.error = "Field is required"
                requiredFieldsStatus = false
            } else {
                textInputName.error = null
            }
        }

        if (editTextExpiryDate.text.isEmpty()) {
            editTextExpiryDate.error = "Field is required"
            requiredFieldsStatus = false
        } else {
            textInputName.error = null
        }

        return requiredFieldsStatus
    }

    private fun sendPaymentData(url: URL) {
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true

        val json = """
        {
            "Cart": "${CartContent.CART}",
            "Name": "${textInputName.editText!!.text}",
            "Surname": "${textInputSurname.editText!!.text}",
            "CardNumber": "${textInputCardNumber.editText!!.text}",
            "CVV": "${textInputCVV.editText!!.text}",
            "CardExpiryDate": "${editTextExpiryDate.text}",
        }
        """

        val outputStream = connection.outputStream
        outputStream.write(json.toByteArray())
        outputStream.close()

        val responseCode = connection.responseCode
        val responseMessage = connection.responseMessage
    }
}