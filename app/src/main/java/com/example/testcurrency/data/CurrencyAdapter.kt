package com.example.testcurrency.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrency.databinding.ItemCurrensyBinding


class CurrencyAdapter(
    private val clickListener: (CurrencyResult) -> Unit
) : ListAdapter<CurrencyResult, CurrencyAdapter.CurrencyViewHolder2>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder2 =
        CurrencyViewHolder2(
            ItemCurrensyBinding.inflate(LayoutInflater.from(parent.context)), clickListener
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder2, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder2(
        private val bindingView: ItemCurrensyBinding,
        private val clickListener: (CurrencyResult) -> Unit
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: CurrencyResult) {

            bindingView.tvName.text = item.name

            itemView.setOnClickListener {
                clickListener(item)

            }

        }
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<CurrencyResult>() {
    override fun areItemsTheSame(oldItem: CurrencyResult, newItem: CurrencyResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CurrencyResult, newItem: CurrencyResult): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.charCode == newItem.charCode
                && oldItem.rate == newItem.rate
                && oldItem.numCod == newItem.numCod
                && oldItem.scale == newItem.scale
    }
}
