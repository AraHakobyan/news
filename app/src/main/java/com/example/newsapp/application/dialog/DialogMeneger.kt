package com.example.newsapp.application.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.newsapp.R


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */

/**
 * notify if something went wrong
 */
fun showErrorDialog(context: Context) {
    AlertDialog.Builder(context).apply {
        setMessage(context.getString(R.string.try_again))
        setCancelable(true)
        setPositiveButton(context.getString(R.string.close)) { dialog, _ ->
            dialog.cancel()
        }
    }.create().show()
}

/**
 * notify if no internet is available
 */
fun showNoInternetDialog(context: Context) {
    AlertDialog.Builder(context).apply {
        setMessage(context.getString(R.string.no_internet_dialog_text))
        setCancelable(true)
        setPositiveButton(context.getString(R.string.close)) { dialog, _ ->
            dialog.cancel()
        }
    }.create().show()
}
