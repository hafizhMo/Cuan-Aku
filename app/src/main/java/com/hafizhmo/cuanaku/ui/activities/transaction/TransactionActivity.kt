package com.hafizhmo.cuanaku.ui.activities.transaction

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hafizhmo.cuanaku.databinding.ActivityTransactionBinding
import com.hafizhmo.cuanaku.model.Category
import com.hafizhmo.cuanaku.utils.SharedPref

class TransactionActivity : AppCompatActivity(), TransactionView {

    private lateinit var binding: ActivityTransactionBinding
    private lateinit var presenter: TransactionPresenter
    private var selectedCategory: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        presenter = TransactionPresenter(this)
        setContentView(binding.root)
        val pref = SharedPref(this)

        binding.submitButton.setOnClickListener {
            if (binding.amountInput.text.toString().isEmpty() ||
                binding.categoryInput.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Field category and amount is still empty!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                showLoading()
                presenter.createTransaction(
                    binding.amountInput.text.toString().toInt(),
                    selectedCategory,
                    pref.getSessionId(),
                    pref.getSessionToken()
                )
            }
        }

        presenter.getCategories()
    }

    override fun submitSuccess(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun submitFailed(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editNotFound(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteNotFound(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun categorySuccess(categories: List<Category.Categories>, message: String) {
        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categories.map { it.slug })
        binding.categoryInput.setAdapter(adapter)
        binding.categoryInput.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, i, _ ->
                selectedCategory = categories[i].id
            }
    }

    override fun categoryEmpty(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun categoryFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.submitButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.submitButton.isEnabled = true
    }
}