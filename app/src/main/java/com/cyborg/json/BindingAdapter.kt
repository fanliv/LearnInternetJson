package com.cyborg.json

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyborg.json.network.ToyNetworkModel
import com.cyborg.json.ui.main.ToysAdapter

@BindingAdapter("bindRecycleView")
fun bindRecycleView(recyclerView: RecyclerView, data: List<ToyNetworkModel>?){
        val adapter = recyclerView.adapter as ToysAdapter
        adapter.submitList(data)
}

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, url: String?){
        url?.let {
                val uri = url.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context).load(uri).into(imageView)
        }
}