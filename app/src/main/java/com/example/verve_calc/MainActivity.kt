package com.example.verve_calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.verve_calc.ui.theme.Verve_CalcTheme
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var currentInput = ""
    private var currentOperator = ""
    private var operand1 = ""
    private var operand2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set click listeners for number buttons
        button0.setOnClickListener { onNumberClick("0") }
        button1.setOnClickListener { onNumberClick("1") }
        button2.setOnClickListener { onNumberClick("2") }
        button3.setOnClickListener { onNumberClick("3") }
        button4.setOnClickListener { onNumberClick("4") }
        button5.setOnClickListener { onNumberClick("5") }
        button6.setOnClickListener { onNumberClick("6") }
        button7.setOnClickListener { onNumberClick("7") }
        button8.setOnClickListener { onNumberClick("8") }
        button9.setOnClickListener { onNumberClick("9") }

        // Set click listener for operator buttons
        buttonAdd.setOnClickListener { onOperatorClick("+") }
        buttonSubtract.setOnClickListener { onOperatorClick("-") }
        buttonMultiply.setOnClickListener { onOperatorClick("*") }
        buttonDivide.setOnClickListener { onOperatorClick("/") }

        // Set click listener for equals button
        buttonEquals.setOnClickListener { onEqualsClick() }

        // Set click listener for clear button
        buttonClear.setOnClickListener { onClearClick() }
    }

    private fun onNumberClick(number: String) {
        currentInput += number
        textViewInput.text = currentInput
    }

    private fun onOperatorClick(operator: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput
            currentInput = ""
            currentOperator = operator
        }
    }

    private fun onEqualsClick() {
        if (currentInput.isNotEmpty() && operand1.isNotEmpty() && currentOperator.isNotEmpty()) {
            operand2 = currentInput

            val result = when (currentOperator) {
                "+" -> operand1.toDouble() + operand2.toDouble()
                "-" -> operand1.toDouble() - operand2.toDouble()
                "*" -> operand1.toDouble() * operand2.toDouble()
                "/" -> {
                    if (operand2.toDouble() != 0.0) {
                        operand1.toDouble() / operand2.toDouble()
                    } else {
                        "Error"
                    }
                }
                else -> "Error"
            }

            currentInput = result.toString()
            textViewInput.text = currentInput
            currentOperator = ""
            operand1 = ""
            operand2 = ""
        }
    }

    private fun onClearClick() {
        currentInput = ""
        currentOperator = ""
        operand1 = ""
        operand2 = ""
        textViewInput.text = "0"
    }
}