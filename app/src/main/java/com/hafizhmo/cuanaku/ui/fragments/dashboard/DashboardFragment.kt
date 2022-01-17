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
import com.hafizhmo.cuanaku.databinding.FragmentDashboardBinding
import com.hafizhmo.cuanaku.model.Budgeting
import com.hafizhmo.cuanaku.ui.activities.budget.BudgetActivity
import com.hafizhmo.cuanaku.ui.activities.budgetdetail.BudgetDetailActivity
import com.hafizhmo.cuanaku.utils.SharedPref
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardFragment : Fragment(), DashboardView {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var presenter: DashboardPresenter

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
            startActivity(Intent(requireContext(), BudgetDetailActivity::class.java))
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val pref = SharedPref(requireContext())
        presenter.getLatestBudget(pref.getSessionId(), pref.getSessionToken())
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSuccess(budget: Budgeting.Budget, message: String) {
        val sdfmonth = DateTimeFormatter.ofPattern("MMMM yyyy")
        val sdfdate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        val dt = LocalDate.parse(budget.created_at, sdfdate)

        binding.textPengeluran.text = budget.total_expenses.toString()
        binding.textSisaBudget.text = budget.remaining_budget.toString()
        binding.textBudgetMonth.text = dt.format(sdfmonth)
    }

    override fun onEmpty(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}