package com.hafizhmo.cuanaku.ui.activities.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ActivityBudgetBinding
import com.hafizhmo.cuanaku.model.Budgetingss
import com.hafizhmo.cuanaku.utils.SharedPref

class BudgetActivity : AppCompatActivity(), BudgetView {
    private lateinit var binding: ActivityBudgetBinding
    private lateinit var presenter: BudgetPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        presenter = BudgetPresenter(this)

        binding.refreshLayout.setOnRefreshListener {
            showLoading()
            val pref = SharedPref(this)
            presenter.getAllBudget(pref.getSessionId(), pref.getSessionToken())
        }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        showLoading()
        val pref = SharedPref(this)
        presenter.getAllBudget(pref.getSessionId(), pref.getSessionToken())
    }

    override fun onSuccess(budget: List<Budgetingss.Budget>, message: String) {
        hideLoading()
        binding.lottieView.visibility = View.GONE
        binding.lottieText.visibility = View.GONE

        binding.budgetRecycler.layoutManager = LinearLayoutManager(this)
        binding.budgetRecycler.adapter = BudgetAdapter(this, budget)
    }

    override fun onEmpty(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
    }

    override fun onFailed(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.budgetRecycler.visibility = View.GONE
        binding.lottieView.visibility = View.VISIBLE
        binding.lottieView.setAnimation(R.raw.loading)
        binding.lottieView.playAnimation()
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.budgetRecycler.visibility = View.VISIBLE
        binding.refreshLayout.isRefreshing = false
    }
}