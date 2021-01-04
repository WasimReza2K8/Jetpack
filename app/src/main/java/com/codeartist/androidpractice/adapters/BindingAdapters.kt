package com.codeartist.androidpractice.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codeartist.androidpractice.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            val imageUri = it.toUri().buildUpon().scheme("https").build()
                    Glide
                            .with(view.context)
                            .load(imageUri)
                            .centerCrop()
                            .apply (RequestOptions()
                                    .placeholder(R.drawable.loading_animation)
                                    .error(R.drawable.ic_broken_image))
                            .into(view)
        }

    }
}