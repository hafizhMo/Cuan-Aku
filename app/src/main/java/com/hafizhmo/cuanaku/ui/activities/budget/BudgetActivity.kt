package com.hafizhmo.cuanaku.ui.activities.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hafizhmo.cuanaku.databinding.ActivityBudgetBinding

class BudgetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBudgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}