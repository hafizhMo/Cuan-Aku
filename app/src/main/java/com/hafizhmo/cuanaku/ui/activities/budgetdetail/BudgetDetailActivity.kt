package com.hafizhmo.cuanaku.ui.activities.budgetdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hafizhmo.cuanaku.databinding.ActivityBudgetDetailBinding
import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.utils.SharedPref

class BudgetDetailActivity : AppCompatActivity(), BudgetDetailView {

    companion object {
        const val KEY_TYPE = "key_type"
        const val KEY_PARCEL = "key_parcel"
    }

    private lateinit var budgeting: Budgetings.Budget
    private lateinit var binding: ActivityBudgetDetailBinding
    private lateinit var presenter: BudgetDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetDetailBinding.inflate(layoutInflater)
        presenter = BudgetDetailPresenter(this)
        setContentView(binding.root)

        val pref = SharedPref(this)

        if (intent.getStringExtra(KEY_TYPE).equals("create")) {
            binding.editButton.visibility = View.GONE
        } else if (intent.getStringExtra(KEY_TYPE).equals("detail")) {
            binding.submitButton.visibility = View.GONE
            budgeting = intent.getParcelableExtra(KEY_PARCEL)!!

            binding.amountInput.setText(budgeting.total_budget.toString())
            binding.amountInput.isEnabled = false
        }

        binding.submitButton.setOnClickListener {
            if (validate()) {
                showLoading()
                presenter.createBudget(
                    binding.amountInput.text.toString().toInt(),
                    pref.getSessionId(),
                    pref.getSessionToken()
                )
            }
        }

        binding.editButton.setOnClickListener {
            if (binding.editButton.text.toString() == "Edit") {
                binding.editButton.text = "Save"
                binding.amountInput.isEnabled = true
                return@setOnClickListener
            }

            if (validate()) {
                showLoading()
                presenter.saveBudget(
                    budgeting.id,
                    binding.amountInput.text.toString().toInt(),
                    pref.getSessionToken()
                )
            }
        }
    }

    override fun createSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun createFailed(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun editNotFound(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editFailed(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.submitButton.isEnabled = false
        binding.editButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.submitButton.isEnabled = true
        binding.editButton.isEnabled = true
    }

    private fun validate(): Boolean {
        if (binding.amountInput.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Field category and amount is still empty!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}