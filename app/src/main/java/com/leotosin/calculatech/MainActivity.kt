package com.leotosin.calculatech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leotosin.calculatech.model.Calculator

class MainActivity : AppCompatActivity() {

    private var calculator :Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}