package com.hafizhmo.cuanaku.ui.fragments.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafizhmo.cuanaku.databinding.ItemListTransactionBinding
import com.hafizhmo.cuanaku.model.Transactions
import com.hafizhmo.cuanaku.ui.activities.transaction.TransactionActivity
import com.hafizhmo.cuanaku.utils.ApiService.Companion.URL_IMAGE

class HistoryAdapter(
    private val context: Context,
    private val transactions: List<Transactions.Transactions>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transactions.Transactions) {
            with(binding){
                tvItemNominal.text = transaction.amount.toString()
                tvItemTimestamp.text = transaction.created_at
                tvItemTitle.text = transaction.category.slug
                Glide.with(context)
                    .load(URL_IMAGE + transaction.category.image)
                    .into(ivItemCategory)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, TransactionActivity::class.java)
                intent.putExtra(TransactionActivity.KEY_TYPE, "detail")
                intent.putExtra(TransactionActivity.KEY_PARCEL, transaction)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListTransactionBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions.get(position))
    }

    override fun getItemCount(): Int = transactions.size

}
