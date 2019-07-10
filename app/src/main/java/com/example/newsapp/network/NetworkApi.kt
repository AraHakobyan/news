package com.example.newsapp.network

import com.example.newsapp.models.NewsResponseModel
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
interface NetworkApi {
    /**
     * get the news
     */
    @GET(NEWS_API)
    fun getNews(): Call<NewsResponseModel>

    /**
     * get the news
     */
    @GET
    fun getRss(@Url rssUrl: String): Call<RssFeed>

}

private const val NEWS_API = "top-headlines?sources=techcrunch&apiKey=97ba815035ae4381b223377b2df975ab"

const val RSS_API: String = "news/video_and_audio/technology/rss.xml"