package com.example.newsapp.utils

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.application.dialog.showErrorDialog


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */

/**
 * Loads and shows image with glide into ImageView
 *
 * @param imageURL remote URL of image
 * @param target   image View to load into it
 */
fun loadRoundedImage(imageURL: String?, target: ImageView?, context: Context) {
    if (target == null) {
        showErrorDialog(context)
        return
    }
    val mRequestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(pxFromDp(context, 16f).toInt()))

    if (TextUtils.isEmpty(imageURL)) {
        Glide.with(context)
            .load(R.drawable.ic_no_image)
            .apply(mRequestOptions)
            .into(target)
    } else {
        Glide.with(context)
            .load(imageURL)
            .apply(mRequestOptions)
            .into(target)
    }
}

private fun pxFromDp(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}