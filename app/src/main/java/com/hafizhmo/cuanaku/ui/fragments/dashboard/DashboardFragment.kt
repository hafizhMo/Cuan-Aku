package com.hafizhmo.cuanaku.ui.fragments.dashboard

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentDashboardBinding
import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.model.TransactionsGroup
import com.hafizhmo.cuanaku.ui.activities.budget.BudgetActivity
import com.hafizhmo.cuanaku.ui.activities.budgetdetail.BudgetDetailActivity
import com.hafizhmo.cuanaku.utils.SharedPref
import com.hafizhmo.cuanaku.utils.StringHelper
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardFragment : Fragment(), DashboardView {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var presenter: DashboardPresenter
    private lateinit var budgeting: Budgetings.Budget

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        presenter = DashboardPresenter(this)

        binding.tvSeeAll.setOnClickListener {
            startActivity(Intent(requireContext(), BudgetActivity::class.java))
        }

        binding.cvBudget.setOnClickListener {
            val intent = Intent(requireContext(), BudgetDetailActivity::class.java)
            intent.putExtra(BudgetDetailActivity.KEY_TYPE, "detail")
            intent.putExtra(BudgetDetailActivity.KEY_PARCEL, budgeting)
            startActivity(intent)
        }

        binding.cvEmpty.setOnClickListener {
            val intent = Intent(requireContext(), BudgetDetailActivity::class.java)
            intent.putExtra(BudgetDetailActivity.KEY_TYPE, "create")
            startActivity(intent)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        showLoading()
        val pref = SharedPref(requireContext())
        presenter.getLatestBudget(pref.getSessionId(), pref.getSessionToken())
        presenter.getTransactionGroup(pref.getSessionId(), pref.getSessionToken())
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSuccess(budget: Budgetings.Budget, message: String) {
        binding.cvBudget.visibility = View.VISIBLE
        binding.cvEmpty.visibility = View.GONE
        budgeting = budget
//        val sdfmonth = DateTimeFormatter.ofPattern("MMMM yyyy")
//        val sdfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
//        val dt = LocalDate.parse(budget.created_at, sdfdate)

        val count = budget.total_expenses
        val totalCount = budget.total_budget
        val percentage = (count.toDouble()/totalCount)*100
        lpi_budget.progress = percentage.toInt()

        binding.textPengeluran.text = StringHelper.convertFormatPrice(budget.total_expenses.toString())
        binding.textSisaBudget.text = StringHelper.convertFormatPrice(budget.remaining_budget.toString())
//        binding.textBudgetMonth.text = dt.format(sdfmonth)
        binding.textBudgetMonth.text = "${budget.month} ${budget.year}"
    }

    override fun onEmpty(message: String) {
        binding.cvBudget.visibility = View.INVISIBLE
    }

    override fun onFailed(message: String) {
        binding.cvBudget.visibility = View.INVISIBLE
    }

    override fun onGroupSuccess(transactions: List<TransactionsGroup.Transactions>, msg: String) {
        hideLoading()
        binding.lottieView.visibility = View.GONE
        binding.lottieText.visibility = View.GONE

        binding.dashboardRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.dashboardRecycler.adapter = DashboardAdapter(requireContext(), transactions)
    }

    override fun onGroupFailed(msg: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = msg
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.lottieView.visibility = View.VISIBLE
        binding.lottieView.setAnimation(R.raw.loading)
        binding.lottieView.playAnimation()
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
    }

}