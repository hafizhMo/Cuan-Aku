package com.hafizhmo.cuanaku.ui.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ActivityMainBinding
import com.hafizhmo.cuanaku.ui.activities.auth.AuthActivity
import com.hafizhmo.cuanaku.ui.activities.transaction.TransactionActivity
import com.hafizhmo.cuanaku.ui.fragments.dashboard.DashboardFragment
import com.hafizhmo.cuanaku.ui.fragments.history.HistoryFragment
import com.hafizhmo.cuanaku.utils.SharedPref

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.background = null
        binding.bnvMain.menu.getItem(1).isEnabled = false

        attachFragment(DashboardFragment())

        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> attachFragment(DashboardFragment())
                R.id.history -> attachFragment(HistoryFragment())
            }
            true
        }

        binding.fabMain.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }

        binding.imageIcon.setOnClickListener {
            SharedPref(this).clearSession()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }

    private fun attachFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main, fragment)
            commit()
        }

}