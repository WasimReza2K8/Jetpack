package com.codeartist.androidpractice.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.codeartist.androidpractice.R;

public class BindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide
                .with(view.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(view);
    }
}
