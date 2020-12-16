package com.codeartist.androidpractice.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.codeartist.androidpractice.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide
                .with(view.context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(view)
    }
}