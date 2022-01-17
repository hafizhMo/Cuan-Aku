package com.hafizhmo.cuanaku.ui.activities.budget

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafizhmo.cuanaku.databinding.ItemListBudgetingBinding
import com.hafizhmo.cuanaku.databinding.ItemListTransactionBinding
import com.hafizhmo.cuanaku.model.Budgetingss
import com.hafizhmo.cuanaku.model.Transactions
import com.hafizhmo.cuanaku.ui.activities.transaction.TransactionActivity
import com.hafizhmo.cuanaku.utils.ApiService.Companion.URL_IMAGE
import com.hafizhmo.cuanaku.utils.StringHelper

class BudgetAdapter(
    private val context: Context,
    private val budget: List<Budgetingss.Budget>
) : RecyclerView.Adapter<BudgetAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBudgetingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(budget: Budgetingss.Budget) {
            with(binding){
                val count = budget.total_expenses
                val totalCount = budget.total_budget
                val percentage = ((count.toDouble()/totalCount)*100).toInt()
                val countNew = StringHelper.convertFormatPrice(count.toString())
                val totalCountNew = StringHelper.convertFormatPrice(totalCount.toString())

                tvItemMonthYear.text = "${budget.month} ${budget.year}"
                tvItemExpensesTotalBudget.text = "$countNew/$totalCountNew"
                tvItemPercentage.text = "$percentage%"
                cpItemBudgeting.progress = percentage
            }

            itemView.setOnClickListener {
                val intent = Intent(context, TransactionActivity::class.java)
                intent.putExtra(TransactionActivity.KEY_TYPE, "detail")
                intent.putExtra(TransactionActivity.KEY_PARCEL, budget)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBudgetingBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(budget.get(position))
    }

    override fun getItemCount(): Int = budget.size

}
