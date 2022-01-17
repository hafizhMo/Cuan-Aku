package com.hafizhmo.cuanaku.ui.activities.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafizhmo.cuanaku.databinding.ItemListProfileBinding
import com.hafizhmo.cuanaku.model.Feature

class ProfileAdapter(
    private val context: Context,
    private val text: MutableList<Feature>,
    private val listener: (Int) -> Unit
) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemListProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int, listener: (Int) -> Unit) {
            with(binding) {
                textTitle.text = text.get(position).title
                switchItem.setOnCheckedChangeListener { compoundButton, b ->
                    if (b)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    else
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                text.get(position).image.let { Glide.with(context).load(it).into(imageIcon) }

                when (text.get(position).title) {
                    "Dark Theme" -> {
                        switchItem.visibility = View.VISIBLE
                        imageIcon2.visibility = View.GONE
                    }
                    "Rate E-Sangu App" -> {
                        textSubtitle.text = "v1.1"
                    }
                }
            }
            itemView.setOnClickListener { listener(position) }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder = ViewHolder(
        ItemListProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position, listener)
    }

    override fun getItemCount(): Int = text.size

}
