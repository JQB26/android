package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearBTN.setOnClickListener {
            calcSpace.text = ""
        }

        backSpaceBTN.setOnClickListener {
            if (calcSpace.text.isNotEmpty()) {
                calcSpace.text = calcSpace.text.dropLast(1)
            }
        }

        zeroBTN.setOnClickListener {
            calcSpace.text = addToInputText("0")
        }

        oneBTN.setOnClickListener {
            calcSpace.text = addToInputText("1")
        }

        twoBTN.setOnClickListener {
            calcSpace.text = addToInputText("2")
        }

        threeBTN.setOnClickListener {
            calcSpace.text = addToInputText("3")
        }

        fourBTN.setOnClickListener {
            calcSpace.text = addToInputText("4")
        }

        fiveBTN.setOnClickListener {
            calcSpace.text = addToInputText("5")
        }

        sixBTN.setOnClickListener {
            calcSpace.text = addToInputText("6")
        }

        sevenBTN.setOnClickListener {
            calcSpace.text = addToInputText("7")
        }

        eightBTN.setOnClickListener {
            calcSpace.text = addToInputText("8")
        }

        nineBTN.setOnClickListener {
            calcSpace.text = addToInputText("9")
        }

        pointBTN.setOnClickListener {
            calcSpace.text = addToInputText(".")
        }

        percentBTN.setOnClickListener {
            calcSpace.text = addToInputText("%")
        }

        exponentBTN.setOnClickListener {
            calcSpace.text = addToInputText("^")
        }

        divideBTN.setOnClickListener {
            calcSpace.text = addToInputText("/")
        }

        multiplyBTN.setOnClickListener {
            calcSpace.text = addToInputText("*")
        }

        subtractBTN.setOnClickListener {
            calcSpace.text = addToInputText("-")
        }

        addBTN.setOnClickListener {
            calcSpace.text = addToInputText("+")
        }

        logBTN.setOnClickListener {
            calcSpace.text = addToInputText("ln")
        }

        equalsBTN.setOnClickListener {
            onEqual()
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${calcSpace.text}$buttonValue"
    }

    private fun onEqual() {
        val expression = ExpressionBuilder(calcSpace.text as String?).build()
        try {
            val result = expression.evaluate()
            calcSpace.text = result.toString()
        } catch (ex: ArithmeticException) {
            calcSpace.text = "Error"
        }
    }

}