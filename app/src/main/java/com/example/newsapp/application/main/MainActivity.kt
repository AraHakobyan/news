@file:Suppress("KDocMissingDocumentation")

package com.example.newsapp.application.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.R
import com.example.newsapp.application.base.BaseActivity
import com.example.newsapp.application.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Boolean.TRUE

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initObservers()
        viewModel.progressLiveData.value = true
        supportFragmentManager.beginTransaction().replace(vFragmentContent.id, NewsFragment())
            .commitAllowingStateLoss()

    }

    private fun initObservers() {
        viewModel.progressLiveData.observe(this, Observer {
            if (it == TRUE) {
                vProgress.visibility = View.VISIBLE
            } else {
                vProgress.visibility = View.GONE
            }

        })
    }
}
