package com.example.coinz.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.News
import com.example.coinz.ui.MainActivity
import com.example.coinz.ui.fragments.NewsDescription
import com.example.coinz.ui.fragments.NewsDescriptionWebView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_item.view.*


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
        Glide.with(actvity).load(data[position].img).into(holder.itemView.imgNews)
        holder.itemView.tvDate.text = data[position].date
        holder.itemView.tvTitleNews.text = data[position].title

        holder.itemView.setOnClickListener {
            val frag = NewsDescriptionWebView()
            val bundle = Bundle()
            bundle.putParcelable("news",data[position])
            frag.arguments = bundle
            (actvity as MainActivity).mainFrame.visibility = View.GONE
            (actvity as MainActivity).newsDescriptionFragHolder.visibility = View.VISIBLE
            (actvity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.newsDescriptionFragHolder,frag).commit()


        }
    }
}
