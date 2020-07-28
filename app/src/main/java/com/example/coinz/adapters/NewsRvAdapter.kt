package com.example.coinz.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinz.R
import com.example.coinz.models.News
import com.example.coinz.ui.MainActivity
import com.example.coinz.ui.fragments.NewsDescription


class NewsRvAdapter(var actvity: Activity, var data: MutableList<News>) :
    RecyclerView.Adapter<NewsRvAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonestration

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(actvity).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: NewsRvAdapter.MyViewHolder, position: Int) {
holder.itemView.setOnClickListener {
    (actvity as MainActivity).replaceFragment(NewsDescription())
}
    }
}
