package com.example.coinz.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.NewsRvAdapter
import com.example.coinz.models.News
import com.example.coinz.retrofit.ApiClient
import com.example.coinz.retrofit.ApiInterface
import com.example.coinz.retrofit.NewsListResponse
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.fragment_news.view.progress_circular_table
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {
    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        getAllNews()

    }

    private fun getAllNews() {
        root.progress_circular_table.visibility = View.VISIBLE
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getNewsList()
        call.enqueue(
            object : Callback<NewsListResponse> {
                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        "هناك مشاكل بالاتصال يرجى المعاودة لاحقا.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("mmm", t.localizedMessage!!)
                    root.progress_circular_table.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<NewsListResponse>,
                    response: Response<NewsListResponse>
                ) {
                    val data = response.body()!!.list
                    Log.e("mmm", data.size.toString())
                    updateAdapter(data)
                    root.progress_circular_table.visibility = View.GONE

                }

            }
        )
    }

    private fun updateAdapter(list: MutableList<News>) {
        root.rvNews.adapter = NewsRvAdapter(activity!!, list)
        root.rvNews.layoutManager = LinearLayoutManager(activity)
    }

}