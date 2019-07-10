package com.example.newsapp.models

import androidx.annotation.StringDef
import com.example.newsapp.models.article.ArticleModel
import com.google.gson.annotations.SerializedName


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
class NewsResponseModel {
    /**
     * @see StatusType
     */
    @SerializedName("status")
    @StatusType
    val status: String? = null

    /**
     * the result list size
     */
    @SerializedName("totalResults")
    val resultCount: Int? = null

    /**
     * the list of articles
     */
    @SerializedName("articles")
    val articles: List<ArticleModel>? = null

    /**
     * error code
     */
    @SerializedName("code")
    val errorCode: String? = null

    /**
     * error message
     */
    @SerializedName("message")
    val errorMessage: String? = null
}

/**
 * the counter process type
 */
@StringDef(STATUS_OK, STATUS_ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class StatusType

/**
 * the response status is OK
 */
const val STATUS_OK: String = "ok"
/**
 * the response status is ERROR
 */
const val STATUS_ERROR: String = "error"
