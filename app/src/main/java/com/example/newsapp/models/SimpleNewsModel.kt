package com.example.newsapp.models

import androidx.annotation.IntDef
import com.example.newsapp.models.article.ArticleModel
import me.toptas.rssconverter.RssItem
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */
@Suppress("KDocMissingDocumentation")
class SimpleNewsModel : Comparable<SimpleNewsModel> {

    @DataModelType
    var modelType: Int? = null

    constructor(articleModel: ArticleModel) {
        title = articleModel.title
        description = articleModel.description
        url = articleModel.url
        imageUrl = articleModel.urlToImage
        date = articleModel.publishedAt
        modelType = TYPE_SIMPLE
    }

    constructor(rssModel: RssItem) {
        title = rssModel.title
        description = rssModel.description
        url = rssModel.link
        imageUrl = rssModel.image
        date = rssModel.publishDate
        modelType = TYPE_RSS
    }

    var title: String? = null
    var description: String? = null
    var url: String? = null
    var imageUrl: String? = null
    var date: String? = null

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    private val rssDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US)

    override fun compareTo(other: SimpleNewsModel): Int {
        return try {
            when (modelType) {
                TYPE_RSS -> {
                    val date: Date = rssDateFormat.parse(date)
                    val otherDate: Date = getOtherDate(other)
                    if (date.time > otherDate.time) 1 else -1

                }
                TYPE_SIMPLE -> {
                    val date: Date = simpleDateFormat.parse(date)
                    val otherDate: Date = getOtherDate(other)
                    if (date.time > otherDate.time) 1 else -1

                }
                else -> {
                    -1
                }

            }

        } catch (ex: Exception) {
            -1
        }


    }

    private fun getOtherDate(other: SimpleNewsModel): Date = when (other.modelType) {
        TYPE_RSS -> {
            rssDateFormat.parse(other.date)
        }
        else -> {
            simpleDateFormat.parse(other.date)
        }
    }
}

@IntDef(TYPE_RSS, TYPE_SIMPLE)
private annotation class DataModelType

private const val TYPE_RSS = 1
private const val TYPE_SIMPLE = 2