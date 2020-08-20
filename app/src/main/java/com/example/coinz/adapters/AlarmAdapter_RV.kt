package com.example.coinz.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Alarm
import com.example.coinz.models.DeleteStatus
import com.example.coinz.models.OnDeleteHandler
import com.example.coinz.retrofit.ApiClient
import com.example.coinz.retrofit.ApiInterface
import com.example.coinz.ui.fragments.AlarmFragment
import kotlinx.android.synthetic.main.alarm_rv_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlarmAdapter_RV(var context: Context, var data: MutableList<Alarm>,var fragment : AlarmFragment) :
    RecyclerView.Adapter<AlarmAdapter_RV.MyViewHolder>() {
    init {
      //  data.reverse()
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvNumber = itemView.tvNumber  ****** for demonestration
        val imgAlarmIconItem = itemView.imgAlarmIconItem
        val tvCurrencyNameTrigger = itemView.tvCurrencyNameTrigger
        val tvAlarmType = itemView.tvAlarmType
        val tvValue = itemView.tvValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.alarm_rv_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: AlarmAdapter_RV.MyViewHolder, position: Int) {

        holder.tvAlarmType.text = getTypeString(data[position].type)
        holder.tvValue.text = data[position].amount.toString()
        holder.tvCurrencyNameTrigger.text = data[position].currencyName
        Glide.with(context).load(data[position].icon).into(holder.imgAlarmIconItem)

        holder.itemView.btnDelete.setOnClickListener{
             ApiClient.getInstance().create(ApiInterface::class.java).deleteTrigger(data[position].id).
             enqueue(object : Callback<DeleteStatus>{
                 override fun onFailure(call: Call<DeleteStatus>, t: Throwable) {
                     Toast.makeText(context,"هناك خطأ في عملية الحذف!!", Toast.LENGTH_SHORT).show()
                     Log.e("mmm",t.localizedMessage!!)
                 }

                 override fun onResponse(
                     call: Call<DeleteStatus>,
                     response: Response<DeleteStatus>
                 ) {
                     Log.e("mmm","-----\n"+response.body()!!.toString()+"\n -----")
                     if (response.body()!!.status.success){
                         Log.e("mmm",response.body().toString())
                         (fragment as OnDeleteHandler).onDeleteSuccess()
                         Toast.makeText(context,"تم الحذف بنجاح", Toast.LENGTH_SHORT).show()
                     }else{
  /*                       Log.e("mmm","Somthing went wrong on checking" +
                                 "\n ${response.isSuccessful}  ${response.body()!!.code}    ||  this is the success ${response.body()!!.success}")*/
                         Toast.makeText(context,"لم يتم الحذف !!!", Toast.LENGTH_SHORT).show()
                     }
                 }

             })

        }
    }
    private fun getTypeString(code: Int): String{
        when(code){
            0 -> return "غير معروف"
            1 -> return "أقل من"
            2 -> return "يساوي"
            3 -> return "أكبر من"
            else -> return "غير معروف"
        }
    }


}
