package com.leotosin.calculatech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.leotosin.calculatech.model.Calculator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var calculator :Calculator = Calculator()
    private var userTypingNumber = false
    private var decimalDelimiterTyped = false

    private var delimiter :String = ","

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtVisor :TextView =  findViewById(R.id.txtNumber);
        txtVisor.text = "0"

        val localization : Locale = resources.configuration.locales[0]
        val format :NumberFormat = NumberFormat.getInstance(localization)

        if (format is DecimalFormat)
        {
            val symbol :DecimalFormatSymbols = format.decimalFormatSymbols
            delimiter = symbol.decimalSeparator.toString()
        }

        var btnDelimiter :Button = findViewById(R.id.btnComma);
        btnDelimiter.text = delimiter

        Toast.makeText(
            this,
            getString(R.string.welcomeMessage),
            Toast.LENGTH_LONG
        ).show()
    }
}