package com.hafizhmo.cuanaku.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.cuanaku.databinding.FragmentHistoryBinding
import com.hafizhmo.cuanaku.model.Transactions
import com.hafizhmo.cuanaku.utils.SharedPref

class HistoryFragment : Fragment(), HistoryView {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var presenter: HistoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        presenter = HistoryPresenter(this)

        binding.refreshLayout.setOnRefreshListener {
            showLoading()
            val pref = SharedPref(requireContext())
            presenter.getAllHistory(pref.getSessionId(), pref.getSessionToken())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        showLoading()
        val pref = SharedPref(requireContext())
        presenter.getAllHistory(pref.getSessionId(), pref.getSessionToken())
    }

    override fun onSuccess(transactions: List<Transactions.Transactions>, message: String) {
        hideLoading()

        binding.historyRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecycler.adapter = HistoryAdapter(requireContext(), transactions)
    }

    override fun onEmpty(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.refreshLayout.isRefreshing = false
    }
}