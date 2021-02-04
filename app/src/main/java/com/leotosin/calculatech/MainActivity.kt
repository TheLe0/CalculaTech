package com.leotosin.calculatech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.leotosin.calculatech.model.Calculator
import com.leotosin.calculatech.model.Operator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var calculator :Calculator = Calculator()
    private var userTypingNumber = false
    private var decimalDelimiterTyped = false

    private var delimiterChar :Char = ','
    private var delimiter :String = ","

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtVisor :TextView =  findViewById(R.id.txtNumber)
        txtVisor.text = "0"

        val localization : Locale = resources.configuration.locales[0]
        val format :NumberFormat = NumberFormat.getInstance(localization)

        if (format is DecimalFormat)
        {
            val symbol :DecimalFormatSymbols = format.decimalFormatSymbols
            delimiterChar = symbol.decimalSeparator
            delimiter = delimiterChar.toString()
        }

        val btnDelimiter :Button = findViewById(R.id.btnComma)
        btnDelimiter.text = delimiter

        Toast.makeText(
            this,
            getString(R.string.welcomeMessage),
            Toast.LENGTH_LONG
        ).show()
    }

    fun clickUndoRedo(view :View)
    {
        val button :Button = view as Button
        val digit :String = button.text.toString()
        val txtVisor :TextView =  findViewById(R.id.txtNumber)

        var result = ""

        when (digit)
        {
            Operator.UNDO ->
            {
                this.calculator.undo()
                result = this.calculator.operating.toString()
            }
            Operator.REDO ->
            {
                this.calculator.redo()
                result = this.calculator.operating.toString()
            }
            else ->
            {
                result = txtVisor.text.toString()
            }
        }

        if (result.endsWith(".0"))
        {
            result = result.substring(0, result.length - 2)
        }

        txtVisor.text = result
    }

    fun clickNumber(view :View)
    {
        val button :Button = view as Button
        val digit :String = button.text.toString()
        val txtVisor :TextView =  findViewById(R.id.txtNumber)
        val currentNumber :String = txtVisor.text.toString()

        if (!userTypingNumber || currentNumber == "0")
        {
            txtVisor.text = digit
            if (digit != "0")
            {
                userTypingNumber = true
            }
        }
        else
        {
            txtVisor.text = currentNumber.plus(digit)
        }
    }

    fun clickOperation(view :View)
    {
        val button :Button = view as Button
        val operation :String = button.text.toString()
        val txtVisor :TextView =  findViewById(R.id.txtNumber)

        if (operation == delimiter && !decimalDelimiterTyped)
        {
            decimalDelimiterTyped = true
            if (!userTypingNumber)
            {
                txtVisor.text = "0".plus(delimiter)
            }
            else
            {
                txtVisor.text = txtVisor.text.toString().plus(delimiter)
            }
            userTypingNumber = true
        }
        else if (operation != delimiter)
        {
            val valueWithoutDelimiter :String = txtVisor.text.toString().replace(delimiterChar, '.')
            calculator.operating = valueWithoutDelimiter.toDouble()
            calculator.performOperation(operation)

            var result :String = calculator.operating.toString()

            if (result.endsWith(".0"))
            {
                result = result.substring(0, result.length - 2)
            }

            txtVisor.text = result.replace('.', delimiterChar)

            userTypingNumber = false
            decimalDelimiterTyped = false
        }
    }

    fun clickMemory(view :View)
    {
        val button :Button = view as Button
        val operation :String = button.text.toString()
        val txtVisor :TextView =  findViewById(R.id.txtNumber);
        val valueWithoutDelimiter :String = txtVisor.text.toString().replace(delimiterChar, '.')

        calculator.operating = valueWithoutDelimiter.toDouble()
        calculator.performMemoryOperation(operation)
        userTypingNumber = false
    }
}