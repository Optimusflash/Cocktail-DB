package com.optimus.cocktaildb.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.optimus.cocktaildb.R

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */

fun ImageView.loadImage(url: String?){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.no_image_found)
        .into(this)
}