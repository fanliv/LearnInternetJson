package com.cyborg.json

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cyborg.json.domain.DomainToy
import com.cyborg.json.ui.main.ToysAdapter

@BindingAdapter("bindRecycleView")
fun bindRecycleView(recyclerView: RecyclerView, data: List<DomainToy>?){
        val adapter = recyclerView.adapter as ToysAdapter
        adapter.submitList(data)
}

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, url: String?){
        url?.let {
                val uri = url.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context).load(uri).apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(imageView)
        }
}