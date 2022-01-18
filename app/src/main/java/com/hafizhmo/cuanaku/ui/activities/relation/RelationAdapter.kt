package com.hafizhmo.cuanaku.ui.activities.relation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafizhmo.cuanaku.databinding.ItemListRelationBinding
import com.hafizhmo.cuanaku.model.Relations

class RelationAdapter(
    private val context: Context,
    private val relations: List<Relations.Relations>
) : RecyclerView.Adapter<RelationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListRelationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(relation: Relations.Relations) {
            with(binding) {
                tvItemName.text = relation.name
                tvItemEmail.text = relation.email
                tvItemStatus.text =
                    if (relation.status == 0) "Waiting Response" else if (relation.status == 1) "Accepted" else "Rejected"

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListRelationBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(relations.get(position))
    }

    override fun getItemCount(): Int = relations.size

}
