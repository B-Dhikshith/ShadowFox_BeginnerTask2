package com.example.calculatorapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare variables at the class level
    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnClear: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Now initialize the declared variables
        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnAdd).setOnClickListener { performOperation("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { performOperation("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { performOperation("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { performOperation("/") }
        btnClear = findViewById(R.id.btnClear)

        btnClear.setOnClickListener {
            etNumber1.text.clear()
            etNumber2.text.clear()
            tvResult.text = "Result:"
        }

    }

    private fun performOperation(operator: String) {
        val num1Text = etNumber1.text.toString().trim()
        val num2Text = etNumber2.text.toString().trim()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            tvResult.text = "Result:"
            return
        }

        val num1: Double
        val num2: Double

        try {
            num1 = num1Text.toDouble()
            num2 = num2Text.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
            tvResult.text = "Result:"
            return
        }

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    tvResult.text = "Result:"
                    return
                } else {
                    num1 / num2
                }
            }
            else -> {
                Toast.makeText(this, "Unknown operation", Toast.LENGTH_SHORT).show()
                return
            }
        }

        tvResult.text = "Result: %.2f".format(result)
    }
}
