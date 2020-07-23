package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinz.R
import com.example.coinz.models.Currency


class CurrencyTableAdapter(var context: Context, var data: MutableList<Currency>) :
    RecyclerView.Adapter<CurrencyTableAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonestration

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.currency_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: CurrencyTableAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
