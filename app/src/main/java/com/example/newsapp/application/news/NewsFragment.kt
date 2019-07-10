package com.example.newsapp.application.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.application.BUNDLE_URL
import com.example.newsapp.application.base.BaseFragment
import com.example.newsapp.application.details.WebViewFragment
import com.example.newsapp.application.listener.OnItemClicked
import com.example.newsapp.application.main.MainActivityViewModel
import com.example.newsapp.models.SimpleNewsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_fragment_layout.*


/**
 * Created by Ara Hakobyan on 7/9/2019.
 * ggTeam
 */
class NewsFragment : BaseFragment(), OnItemClicked<SimpleNewsModel> {

    private lateinit var activityViewModel: MainActivityViewModel

    @Suppress("KDocMissingDocumentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        activityViewModel.getInfo(context!!)
    }

    @Suppress("KDocMissingDocumentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_fragment_layout, container, false)
    }

    @Suppress("KDocMissingDocumentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.newsLiveData?.observe(this, Observer {
            it ?: return@Observer
            activityViewModel.progressLiveData.postValue(false)
            vNewsRecycler.apply {
                adapter = NewsAdapter(it, context, this@NewsFragment)
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        })
    }

    override fun onClicked(key: Int, data: SimpleNewsModel, clickedView: View) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addSharedElement(clickedView, resources.getString(R.string.transition_name))
            ?.addToBackStack(null)
            ?.replace(activity!!.vFragmentContent.id, WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_URL, data.url)
                }
            })?.commit()
    }

}