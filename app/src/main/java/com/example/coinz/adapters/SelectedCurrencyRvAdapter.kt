package com.example.coinz.adapters

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Currency
import com.example.coinz.ui.MainActivity
import com.example.coinz.ui.dialog.OnSelectDialogInterface
import com.example.coinz.ui.dialog.SelectCurrencyDialog
import com.example.coinz.ui.fragments.PriceFragment
import kotlinx.android.synthetic.main.card_currency.view.*


class SelectedCurrencyRvAdapter(var context: Context, var data: MutableList<Currency>, val fragment: Fragment) :
    RecyclerView.Adapter<SelectedCurrencyRvAdapter.MyViewHolder>(),OnSelectDialogInterface {

    val editor = (context as MainActivity).sharedPreferences.edit()

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
        holder.itemView.setOnLongClickListener {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("تنويه").setMessage("تأكيد عملية الازالة؟").setIcon(R.drawable.ic_warning).
            setPositiveButton("نعم") { _, _ ->
                //delete from selected
                val activity = (context as MainActivity)
                activity.sharedPreferences.edit().remove("c_code_$position").apply()
                val frag = (fragment as PriceFragment)
                frag.data[position] = Currency(null, null, null, null, null)
                frag.updateSelectedAdapter()
            }.setCancelable(true).setNegativeButton("لا",null)
                .show()


           return@setOnLongClickListener true
        }

    }

    override fun updateSelectedAdapter(position : Int,currency: Currency) {
        data[position] = currency
        editor.putString("c_code_$position",currency.code).apply()
        Log.e("shmm","c_code_"+position + " and code = ${currency.code}")
        notifyDataSetChanged()
    }


}
