package com.hafizhmo.cuanaku.ui.fragments.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentDashboardBinding
import com.hafizhmo.cuanaku.model.Expenses

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        val ftitle = resources.getStringArray(R.array.feature_title)
        val fcolor = resources.obtainTypedArray(R.array.feature_color)
        val fimage = resources.obtainTypedArray(R.array.feature_image)
        val categories: ArrayList<Expenses.Category> = arrayListOf()

        categories.clear()
        for (i in ftitle.indices)
            categories.add(Expenses.Category(ftitle[i], fcolor.getResourceId(i, 0), fimage.getResourceId(i, 0)))
        fcolor.recycle()
        fimage.recycle()

        val transactions : ArrayList<Expenses> = arrayListOf()
        transactions.add(Expenses("4 seconds ago", "Rp 20,000", categories.get(0)))
        transactions.add(Expenses("30 minutes ago", "Rp 10,000", categories.get(1)))
        transactions.add(Expenses("1 hours ago", "Rp 100,000", categories.get(2)))
        transactions.add(Expenses("07.09 PM", "Rp 98,000", categories.get(3)))
        transactions.add(Expenses("04.30 PM", "Rp 2,000", categories.get(4)))
        transactions.add(Expenses("10.01 AM", "Rp 50,700", categories.get(5)))
        transactions.add(Expenses("08:00 AM", "Rp 50,000", categories.get(6)))

        binding.rvDashboard.apply {
            adapter = TransactionAdapter(transactions, requireContext())
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        return binding.root
    }

}