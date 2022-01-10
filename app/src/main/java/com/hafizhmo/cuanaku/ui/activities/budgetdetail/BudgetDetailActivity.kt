package com.hafizhmo.cuanaku.ui.activities.budgetdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hafizhmo.cuanaku.databinding.ActivityBudgetDetailBinding

class BudgetDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBudgetDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}