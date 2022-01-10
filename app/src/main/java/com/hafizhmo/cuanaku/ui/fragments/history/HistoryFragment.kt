package com.hafizhmo.cuanaku.ui.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentHistoryBinding
import com.hafizhmo.cuanaku.model.Transaction

class HistoryFragment : Fragment(), HistoryView {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onSuccess(transactions: Transaction.Transactions, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onEmpty(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}