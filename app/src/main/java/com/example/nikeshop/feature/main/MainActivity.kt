package com.example.nikeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : NikeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}