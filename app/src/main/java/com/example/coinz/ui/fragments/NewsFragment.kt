package com.example.coinz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.NewsRvAdapter
import com.example.coinz.models.News
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*


class NewsFragment : Fragment() {
    lateinit var root: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_news, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        //dummy data
        val news = News()
        val data = mutableListOf<News>()
        data.add(news);data.add(news);data.add(news);data.add(news);data.add(news);data.add(news);
        data.add(news);data.add(news);data.add(news);data.add(news);data.add(news);data.add(news);
        rvNews.adapter = NewsRvAdapter(activity!!,data)
        rvNews.layoutManager = LinearLayoutManager(activity)
        root.test.setOnClickListener {
            (activity as MainActivity).replaceFragment(NewsDescription())
        }


    }

}