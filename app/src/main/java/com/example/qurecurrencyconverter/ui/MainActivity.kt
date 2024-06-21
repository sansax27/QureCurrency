package com.example.qurecurrencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qurecurrencyconverter.R
import com.example.qurecurrencyconverter.adapter.MainActivityVPAdapter
import com.example.qurecurrencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vpMainSwipe.adapter = MainActivityVPAdapter(this)
    }
}