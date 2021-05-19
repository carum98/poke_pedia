package com.example.pokepedia.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokepedia.R
import com.jakewharton.rxbinding4.view.visibility

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PokePedia)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}