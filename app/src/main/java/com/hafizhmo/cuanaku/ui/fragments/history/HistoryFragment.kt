package com.hafizhmo.cuanaku.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.cuanaku.R
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
        binding.lottieView.visibility = View.GONE
        binding.lottieText.visibility = View.GONE

        binding.historyRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecycler.adapter = HistoryAdapter(requireContext(), transactions)
    }

    override fun onEmpty(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
        binding.historyRecycler.visibility = View.GONE
    }

    override fun onFailed(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
        binding.historyRecycler.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.historyRecycler.visibility = View.GONE
        binding.lottieView.visibility = View.VISIBLE
        binding.lottieView.setAnimation(R.raw.loading)
        binding.lottieView.playAnimation()
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.historyRecycler.visibility = View.VISIBLE
        binding.refreshLayout.isRefreshing = false
    }
}