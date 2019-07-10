package com.example.newsapp.application.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.application.BUNDLE_URL
import com.example.newsapp.application.base.BaseFragment
import com.example.newsapp.application.main.MainActivityViewModel
import kotlinx.android.synthetic.main.web_view_layout.*
import android.webkit.WebView


/**
 * Created by Ara Hakobyan on 7/10/2019.
 * ggTeam
 */
class WebViewFragment : BaseFragment() {

    private lateinit var activityViewModel: MainActivityViewModel
    private var url: String? = null

    @Suppress("KDocMissingDocumentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        url = arguments?.getString(BUNDLE_URL)
    }

    @Suppress("KDocMissingDocumentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.newsapp.R.layout.web_view_layout, container, false)
    }

    @Suppress("KDocMissingDocumentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.progressLiveData.postValue(true)
        url?.let {
            vWebView.apply {
                loadUrl(it)
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        activityViewModel.progressLiveData.postValue(false)
                    }
                }
                settings.javaScriptEnabled = true
            }
        }

    }
}
