package com.manapps.mandroid.moviesapp_mvc_ret.utils

import android.content.Context
import android.transition.CircularPropagation
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.manapps.mandroid.moviesapp_mvc_ret.R

object DownloadImage {
    // usage-guide
    //DownloadImage.loadImage(holder.profileImgView,holder.profileTextView,alt_text,assignedByProfileImage,);
    fun loadImage(imageView :ImageView ,context: Context, url: String?) {
        try {

            // is storing base url in strings file a good idea ? ok : alternative
            var imageUrl=context.resources.getString(R.string.IMAGE_BASE_URL)
            imageUrl +=url
            val requestOptions: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
            Glide.with(imageView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(imageView)
        } catch (e: NullPointerException) {
            Log.e("e", e.toString())
        } catch (e: Exception) {
            Log.e("e", e.toString())
        }
    }
}
