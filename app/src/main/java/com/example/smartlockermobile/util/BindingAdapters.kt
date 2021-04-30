package com.example.smartlockermobile.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smartlockermobile.R
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import com.example.smartlockermobile.ui.tools.ToolsGridAdapter

@BindingAdapter("toolsListData")
fun bindToolsRecyclerView(recyclerView: RecyclerView, data: List<ToolResponse>?) {
    val adapter = recyclerView.adapter as ToolsGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
