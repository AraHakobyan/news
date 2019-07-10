package com.example.newsapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import me.toptas.rssconverter.RssConverterFactory


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */


/**
 * @return the network Api interface to use with
 */
var API_SERVICE: NetworkApi = getApiService()

/**
 * Get API Service
 * @return API Service
 */
private fun getApiService(): NetworkApi = provideRetrofit(
    provideOkHttpClient(),
    provideGsonConverterFactory(),
    provideRxJavaCallAdapterFactory()
).create(NetworkApi::class.java)

/**
 * @return Created GSon converter factory for retrofit builder.
 */
private fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

/**
 * @return Created OK HTTP Client factory for retrofit builder.
 */
private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
    .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
    .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

/**
 * @return Created RX Java factory for retrofit builder.
 */
private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

/**
 * Get Retrofit Instance
 * @param okhttpClient         client as OkHttpClient
 * @param gsonConverterFactory converterFactory as GsonConverterFactory
 * @param adapterFactory       callAdapterFactory as RxJava2CallAdapterFactory
 */
private fun provideRetrofit(
    okhttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    adapterFactory: RxJava2CallAdapterFactory
): Retrofit = Retrofit.Builder()
    .addConverterFactory(gsonConverterFactory)
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(adapterFactory)
    .addConverterFactory(RssConverterFactory.create())
    .client(okhttpClient)
    .build()

private const val BASE_URL = "https://newsapi.org/v2/"
private const val TIMEOUT = 60