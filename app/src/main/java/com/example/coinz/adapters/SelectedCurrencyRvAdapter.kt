package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinz.R
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.card_currency.view.*


class SelectedCurrencyRvAdapter(var context: Context, var data: MutableList<Currency>) :
    RecyclerView.Adapter<SelectedCurrencyRvAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonestration
        val tvName = itemView.tvCurrencyName
        val tvTrade = itemView.tvTradeAmount
        val img = itemView.imgCurrency

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_currency, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: SelectedCurrencyRvAdapter.MyViewHolder, position: Int) {

    }
}
