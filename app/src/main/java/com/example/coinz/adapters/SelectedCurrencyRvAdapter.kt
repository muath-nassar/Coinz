package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Currency
import com.example.coinz.ui.MainActivity
import com.example.coinz.ui.dialog.OnSelectDialogInterface
import com.example.coinz.ui.dialog.SelectCurrencyDialog
import kotlinx.android.synthetic.main.card_currency.view.*


class SelectedCurrencyRvAdapter(var context: Context, var data: MutableList<Currency>) :
    RecyclerView.Adapter<SelectedCurrencyRvAdapter.MyViewHolder>(),OnSelectDialogInterface {

    lateinit var dialog: SelectCurrencyDialog
    val currencyList = (context as MainActivity).currencyList

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
        if (data[position].name != null){
            //set background for holder
            holder.itemView.backgroundTintList = null
            when(position){
                0 -> holder.itemView.background = context.getDrawable(R.drawable.shape_green_card)
                1 -> holder.itemView.background = context.getDrawable(R.drawable.shape_orange_card)
                2 -> holder.itemView.background = context.getDrawable(R.drawable.shape_purple_card)
                3 -> holder.itemView.background = context.getDrawable(R.drawable.shape_blue_card)
            }
            //set content
            holder.tvName.visibility = View.VISIBLE
            holder.tvName.text = data[position].name
            holder.tvTrade.visibility = View.VISIBLE
            holder.tvTrade.text = data[position].trading.toString()
            Glide.with(context).load(data[position].img).into(holder.img)
            holder.img.layoutParams.height= 100
            holder.img.layoutParams.width=100
        }
        holder.itemView.setOnClickListener {
            dialog = SelectCurrencyDialog(context)
            dialog.showDialog(this,position)

        }

    }

    override fun updateSelectedAdapter(position : Int,currency: Currency) {
        data[position] = currency
        notifyDataSetChanged()
    }

}
