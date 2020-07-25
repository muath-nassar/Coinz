package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinz.R
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.card_currency.view.*
import kotlinx.android.synthetic.main.currency_row.view.*


class CurrencyTableAdapter(var context: Context, var data: MutableList<Currency>) :
    RecyclerView.Adapter<CurrencyTableAdapter.MyViewHolder>() {
     private var index = 0
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonstration
        val tvName = itemView.tvTableCuName
        val tvTrade = itemView.tvTableTrade
        val img = itemView.imgTableCu

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.currency_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: CurrencyTableAdapter.MyViewHolder, position: Int) {

    }
}
