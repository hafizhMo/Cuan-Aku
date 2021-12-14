package com.hafizhmo.cuanaku.ui.activities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafizhmo.cuanaku.databinding.ItemGridCategoryBinding
import java.util.ArrayList

class CategoryAdapter(
    private val nums: ArrayList<String>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemGridCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(num: String, listener: (String) -> Unit) {
            binding.tvItem.text = num
            itemView.setOnClickListener { listener(num) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGridCategoryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(nums[position], listener)
    }

    override fun getItemCount(): Int = nums.size

}
