package com.example.coinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Currency
import com.example.coinz.ui.dialog.OnSelectDialogInterface
import com.example.coinz.ui.dialog.SelectCurrencyDialog
import kotlinx.android.synthetic.main.spinner_select_currency.view.*


class DialogSelectCurrencyAdapter(
    var context: Context,
    var data: MutableList<Currency>,
    var dialog: SelectCurrencyDialog,
    var selectedCurrencyRvAdapter: SelectedCurrencyRvAdapter,
    var positionOfSelectedCurrencyRvAdapter: Int
) :
    RecyclerView.Adapter<DialogSelectCurrencyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonestration
        val spinnerItemName = itemView.spinnerItemName
        val spinnerIcon = itemView.spinnerIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.spinner_select_currency, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: DialogSelectCurrencyAdapter.MyViewHolder, position: Int) {
        holder.spinnerItemName.text = data[position].name
        Glide.with(context).load(data[position].img).into(holder.spinnerIcon)
        holder.itemView.setOnClickListener {
            val currency = data[position]
            (selectedCurrencyRvAdapter as OnSelectDialogInterface).updateSelectedAdapter(positionOfSelectedCurrencyRvAdapter,currency)
            dialog.cancelDialog()
        }
    }
}
