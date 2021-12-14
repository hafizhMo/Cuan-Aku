package com.hafizhmo.cuanaku.ui.fragments.dashboard

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ItemListTransactionBinding
import com.hafizhmo.cuanaku.model.Expenses
import java.util.ArrayList

class TransactionAdapter(
    private val transactions: ArrayList<Expenses>,
    private val context: Context
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(expenses: Expenses) {
            with(binding) {
                tvItemNominal.text = expenses.nominal
                tvItemTimestamp.text = expenses.timestamp
                tvItemTitle.text = expenses.category.name
                ivItemCategory.setImageResource(expenses.category.image)
                ivItemCategory.setBackgroundColor(
                    ContextCompat.getColor(context, expenses.category.color)
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.ViewHolder = ViewHolder(
        ItemListTransactionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        holder.onBind(transactions.get(position))
    }

    override fun getItemCount(): Int = transactions.size

}
