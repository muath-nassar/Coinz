package com.example.coinz.ui.dialog

import android.app.Dialog
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.DialogSelectCurrencyAdapter
import com.example.coinz.adapters.SelectedCurrencyRvAdapter
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.select_currency_dialog.*

class SelectCurrencyDialog(val context: Context) {
    private val dialog = Dialog(context)
    val data = (context as MainActivity).currencyList
    fun showDialog(selectedCurrencyRvAdapter: SelectedCurrencyRvAdapter,selectedPosition: Int) {
        dialog.setContentView(R.layout.select_currency_dialog)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.rvSelectDialog.adapter = DialogSelectCurrencyAdapter(context,data,this,selectedCurrencyRvAdapter,selectedPosition)
        dialog.rvSelectDialog.layoutManager = LinearLayoutManager(context)
        dialog.show()


}
    fun cancelDialog(){
        dialog.cancel()
    }
}