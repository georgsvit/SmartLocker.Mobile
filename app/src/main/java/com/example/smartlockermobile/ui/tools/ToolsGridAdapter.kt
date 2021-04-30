package com.example.smartlockermobile.ui.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import com.example.smartlockermobile.databinding.ToolViewItemBinding

class ToolsGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<ToolResponse, ToolsGridAdapter.ToolsViewHolder>(DiffCallback) {
    class ToolsViewHolder(private var binding: ToolViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tool: ToolResponse) {
            binding.tool = tool
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (tool: ToolResponse) -> Unit) {
        fun onClick(tool: ToolResponse) = clickListener(tool)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ToolResponse>() {
        override fun areItemsTheSame(oldItem: ToolResponse, newItem: ToolResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ToolResponse, newItem: ToolResponse): Boolean {
            return oldItem.Id == newItem.Id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsViewHolder {
        return ToolsViewHolder(ToolViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ToolsViewHolder, position: Int) {
        val tool = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(tool)
        }
        holder.bind(tool)
    }
}