@file:Suppress("KDocMissingDocumentation")

package com.example.newsapp.application.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.application.NEWS_ITEM_CLICKED
import com.example.newsapp.application.listener.OnItemClicked
import com.example.newsapp.models.SimpleNewsModel
import com.example.newsapp.models.article.ArticleModel
import com.example.newsapp.utils.loadRoundedImage


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */
class NewsAdapter(
    private val list: List<SimpleNewsModel>,
    private val context: Context,
    private val itemClickListener: OnItemClicked<SimpleNewsModel>
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_layout, parent, false)
        return ViewHolder(itemView = view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: SimpleNewsModel = list[position]
        holder.title.text = data.title
        holder.description.text = data.description
        loadRoundedImage(data.imageUrl, holder.image, context)
        holder.itemView.setOnClickListener {
            itemClickListener.onClicked(NEWS_ITEM_CLICKED, data, it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.news_image)
        var title: TextView = itemView.findViewById(R.id.news_title)
        var description: TextView = itemView.findViewById(R.id.news_description)
    }
}