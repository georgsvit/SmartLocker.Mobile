package com.example.smartlockermobile.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartlockermobile.data.network.dto.responses.NotificationResponse
import com.example.smartlockermobile.databinding.NotificationViewItemBinding

class NotificationsGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<NotificationResponse, NotificationsGridAdapter.NotificationsViewHolder>(DiffCallback) {
    class NotificationsViewHolder(private var binding: NotificationViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: NotificationResponse) {
            binding.notification = notification
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (notification: NotificationResponse) -> Unit) {
        fun onClick(notification: NotificationResponse) = clickListener(notification)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NotificationResponse>() {
        override fun areItemsTheSame(oldItem: NotificationResponse, newItem: NotificationResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NotificationResponse, newItem: NotificationResponse): Boolean {
            return oldItem.Id == newItem.Id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        return NotificationsViewHolder(NotificationViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val notification = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(notification)
        }
        holder.bind(notification)
    }
}