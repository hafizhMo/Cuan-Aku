package com.hafizhmo.cuanaku.ui.activities.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hafizhmo.cuanaku.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}