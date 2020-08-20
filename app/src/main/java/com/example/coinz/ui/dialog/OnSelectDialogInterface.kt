package com.example.coinz.ui.dialog

import com.example.coinz.models.Currency

interface OnSelectDialogInterface {

    fun updateSelectedAdapter(position: Int, currency: Currency)
}
