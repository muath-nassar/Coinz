package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.card_currency.view.*
import kotlinx.android.synthetic.main.currency_row.view.*


class CurrencyTableAdapter(var context: Context, var data: MutableList<Currency>) :
    RecyclerView.Adapter<CurrencyTableAdapter.MyViewHolder>() {
    companion object{
        private var index = 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonstration
        val tvName = itemView.tvTableCuName
        val tvTrade = itemView.tvTableTrade
        val img = itemView.imgTableCu
        val tvTableIndex = itemView.tvTableIndex
        val tvTablePrice= itemView.tvTablePrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.currency_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: CurrencyTableAdapter.MyViewHolder, position: Int) {
        index++
        holder.tvTableIndex.text = (position+1).toString()
        Glide.with(context).load(data[position].img).into(holder.img)
        holder.tvName.text = data[position].name
        holder.tvTablePrice.text = "$ "+data[position].value.toString()
        holder.tvTrade.text = data[position].trading.toString()



    }
}
