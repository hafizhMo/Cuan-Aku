package com.hafizhmo.cuanaku.ui.activities.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hafizhmo.cuanaku.ui.activities.main.MainActivity
import com.hafizhmo.cuanaku.databinding.ActivitySplashScreenBinding
import com.hafizhmo.cuanaku.ui.activities.auth.AuthActivity
import com.hafizhmo.cuanaku.utils.SharedPref

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (SharedPref(this).isLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, AuthActivity::class.java)
            startActivity(i)
            finish()
        }, 1000)

    }
}