package com.example.newsapp.application.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.application.dialog.showErrorDialog
import com.example.newsapp.application.dialog.showNoInternetDialog
import com.example.newsapp.models.SimpleNewsModel
import com.example.newsapp.models.article.ArticleModel
import com.example.newsapp.utils.isNetworkAvailableAndConnected


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
class MainActivityViewModel : ViewModel() {

    /**
     * live data for news articles list
     */
    val newsLiveData: MutableLiveData<List<SimpleNewsModel>>? = MutableLiveData()

    /**
     * show the progress dialog
     */
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * get the news and rss data
     */
    fun getInfo(context: Context) {
        getNews(context)
        getRss(context)
    }

    private fun getNews(context: Context) {
        if (!isNetworkAvailableAndConnected(context = context)) {
            showNoInternetDialog(context)
            return
        }
        MainRepository.instance.getNews(newsLiveData ?: return showErrorDialog(context), context)
    }

    /**
     * get the rss data
     */
    private fun getRss(context: Context) {
        if (!isNetworkAvailableAndConnected(context = context)) {
            showNoInternetDialog(context)
            return
        }
        MainRepository.instance.getRss(newsLiveData ?: return showErrorDialog(context), context)
    }

}