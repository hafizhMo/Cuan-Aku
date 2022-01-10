package com.hafizhmo.cuanaku.ui.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hafizhmo.cuanaku.databinding.FragmentDashboardBinding
import com.hafizhmo.cuanaku.model.Budgeting

class DashboardFragment : Fragment(), DashboardView {

    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onSuccess(budget: Budgeting.Budget, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onEmpty(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}