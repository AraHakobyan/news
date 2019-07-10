package com.example.newsapp.application.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.application.dialog.showErrorDialog
import com.example.newsapp.models.NewsResponseModel
import com.example.newsapp.models.STATUS_OK
import com.example.newsapp.models.SimpleNewsModel
import com.example.newsapp.models.article.ArticleModel
import com.example.newsapp.network.API_SERVICE
import com.example.newsapp.network.NetworkApi
import com.example.newsapp.network.RSS_API
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.util.concurrent.atomic.AtomicBoolean


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
class MainRepository {

    private val simpleList: MutableList<SimpleNewsModel> = mutableListOf()
    private val canShow: AtomicBoolean = AtomicBoolean(false)


    /**
     * make call and get the news data
     */
    fun getNews(liveData: MutableLiveData<List<SimpleNewsModel>>, context: Context?) {
        val newsCall: Call<NewsResponseModel> =
            API_SERVICE.getNews()
        newsCall.enqueue(object : Callback<NewsResponseModel> {
            override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
                showErrorDialog(context ?: return)
            }

            override fun onResponse(call: Call<NewsResponseModel>, response: Response<NewsResponseModel>) {
                if (response.isSuccessful && response.body()?.status?.equals(STATUS_OK) == TRUE) {
                    addArticlesToSimpleList(response.body()?.articles)
                    showData(liveData)

                }
            }

        })
    }

    private fun showData(liveData: MutableLiveData<List<SimpleNewsModel>>) {
        if (canShow.get() == TRUE) {
            canShow.set(FALSE)
            liveData.postValue(simpleList.sorted())
        } else {
            canShow.set(TRUE)
        }
    }

    private fun addArticlesToSimpleList(articles: List<ArticleModel>?) {
        articles?.forEach {
            simpleList.add(SimpleNewsModel(it))
        }
    }

    /**
     * make call and get the news data
     */
    fun getRss(liveData: MutableLiveData<List<SimpleNewsModel>>, context: Context?) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://feeds.bbci.co.uk/")
            .addConverterFactory(RssConverterFactory.create())
            .build()

        val service = retrofit.create(NetworkApi::class.java)

        service.getRss(RSS_API).enqueue(object : Callback<RssFeed> {
            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                showErrorDialog(context ?: return)
            }

            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                addRssToSimpleList(response.body()?.items)
                showData(liveData)
            }

        })
    }

    private fun addRssToSimpleList(articles: List<RssItem>?) {
        articles?.forEach {
            simpleList.add(SimpleNewsModel(it))
        }
    }

    private object Holder {
        val INSTANCE = MainRepository()
    }

    companion object {

        /**
         * the instance of Main repository
         */
        val instance: MainRepository by lazy { Holder.INSTANCE }

    }
}

private const val NONE: Int = 0
private const val ONE_RESPONSE_FINISHED: Int = 1
private const val DONE: Int = 2
