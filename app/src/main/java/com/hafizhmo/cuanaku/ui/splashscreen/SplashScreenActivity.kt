package com.hafizhmo.cuanaku.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hafizhmo.cuanaku.MainActivity
import com.hafizhmo.cuanaku.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 1000)

    }
}