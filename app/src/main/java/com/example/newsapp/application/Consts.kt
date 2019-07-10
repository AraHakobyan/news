package com.example.newsapp.application

import androidx.annotation.IntDef


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */
const val BUNDLE_URL: String = "BUNDLE_URL"

/**
 * on recycler item clicked key annotation
 */
@IntDef(NEWS_ITEM_CLICKED)
annotation class OnItemClickedType

/**
 * on news list item clicked key
 */
const val NEWS_ITEM_CLICKED: Int = 1