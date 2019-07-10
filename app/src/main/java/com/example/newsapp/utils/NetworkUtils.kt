package com.example.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */

/**
 * Check's device's internet connected status
 * @param context context
 * @return connected or not
 */
fun isNetworkAvailableAndConnected(context: Context?): Boolean {
    context ?: return false
    val networkInfo = getNetworkInfo(context)
    val isNetworkAvailable = networkInfo != null
    return isNetworkAvailable && networkInfo!!.isConnected
}

/**
 * Get the network info
 * @param context the context
 * @return ActiveNetworkInfo | Null
 */
private fun getNetworkInfo(context: Context?): NetworkInfo? {
    val cm: ConnectivityManager? =
        context?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm?.activeNetworkInfo
}