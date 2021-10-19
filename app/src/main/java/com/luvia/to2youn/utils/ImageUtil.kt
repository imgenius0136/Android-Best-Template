package com.luvia.to2youn.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.luvia.to2youn.R

class ImageUtil {
    companion object {
        fun setImageLoadCircle(context: Context, imageView: ImageView, url: String?){
            if(url != null){
                Glide.with(context)
                    .load(url)
                    .circleCrop()
                    .into(imageView)
            }else{
                Glide.with(context)
                    .load(ContextCompat.getDrawable(context, R.drawable.ic_baseline_account_circle_24))
                    .circleCrop()
                    .into(imageView)
            }

        }
    }
}