package com.cyborg.json.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.json.databinding.ToyItemBinding
import com.cyborg.json.network.ToyNetworkModel

class ToysAdapter: ListAdapter<ToyNetworkModel, ToysAdapter.ToysViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToysViewHolder {
        return ToysViewHolder(ToyItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ToysViewHolder, position: Int) {
        val toy = getItem(position)
        holder.bind(toy)
    }
    class ToysViewHolder(private val binding: ToyItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(toy: ToyNetworkModel){
            binding.toy = toy
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback: DiffUtil.ItemCallback<ToyNetworkModel>() {
        override fun areItemsTheSame(oldItem: ToyNetworkModel, newItem: ToyNetworkModel): Boolean {
            return oldItem===newItem
        }

        override fun areContentsTheSame(
            oldItem: ToyNetworkModel,
            newItem: ToyNetworkModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}