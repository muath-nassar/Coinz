package com.example.coinz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.News
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news_description_web_view.*
import kotlinx.android.synthetic.main.fragment_news_description_web_view.view.*


class NewsDescriptionWebView : Fragment() {
    lateinit var root: View
    lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle!= null){
            news = bundle.getParcelable("news")!!
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_news_description_web_view, container, false)
        updateUI(news)
        root.btnBack.setOnClickListener {
            (activity as MainActivity).newsDescriptionFragHolder.visibility = View.GONE
            (activity as MainActivity).mainFrame.visibility = View.VISIBLE
        }
        return root
    }
    private  fun updateUI(news: News){
       root.tvTitle.text = news.title
        root.tvDate.text = news.date
        Glide.with(activity!!).load(news.img).into(root.imgNews)
        root.webView.loadData(news.description!!,null,null)
    }




}