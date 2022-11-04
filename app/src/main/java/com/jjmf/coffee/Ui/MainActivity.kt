package com.jjmf.coffee.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjmf.coffee.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        events()
    }

    private fun events() {
        binding
    }
}