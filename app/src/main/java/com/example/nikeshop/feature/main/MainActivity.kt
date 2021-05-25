package com.example.nikeshop.feature.main

import android.os.Bundle
import com.example.nikeshop.NikeActivity
import com.example.nikeshop.R

class MainActivity : NikeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}