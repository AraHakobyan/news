package com.example.newsapp.models.article

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.US
import java.lang.Exception


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
class ArticleModel : Comparable<ArticleModel> {

    @Suppress("KDocMissingDocumentation")
    @SerializedName("source")
    val source: SourceModel? = null

    /**
     * author of this article
     */
    @SerializedName("author")
    val author: String? = null

    /**
     * the article title
     */
    @SerializedName("title")
    val title: String? = null

    @Suppress("KDocMissingDocumentation")
    @SerializedName("description")
    val description: String? = null

    /**
     * base article url
     */
    @SerializedName("url")
    val url: String? = null

    /**
     * image for this article
     */
    @SerializedName("urlToImage")
    val urlToImage: String? = null

    /**
     * published data -> ex "2019-07-09T13:00:47Z"
     */
    @SerializedName("publishedAt")
    val publishedAt: String? = null

    @Suppress("KDocMissingDocumentation")
    @SerializedName("content")
    val content: String? = null

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", US)

    override fun compareTo(other: ArticleModel): Int {
        return try {
            val date: Date = dateFormat.parse(publishedAt)
            val otherDate: Date = dateFormat.parse(other.publishedAt)
            if (date.time > otherDate.time) 1 else -1
        } catch (ex: Exception) {
            -1
        }

    }


}