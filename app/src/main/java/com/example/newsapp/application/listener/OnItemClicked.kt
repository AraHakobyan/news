package com.example.newsapp.application.listener

import android.view.View
import com.example.newsapp.application.OnItemClickedType


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */
interface OnItemClicked<T> {
    /**
     * the list item is clicked
     * [key] is the type of list
     * [data] is the clicked item data
     * [clickedView] is the clicked item view
     */
    fun onClicked(@OnItemClickedType key: Int, data: T, clickedView: View)
}