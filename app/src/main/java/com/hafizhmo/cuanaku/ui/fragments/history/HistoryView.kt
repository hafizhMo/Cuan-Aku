package com.hafizhmo.cuanaku.ui.fragments.history

import com.hafizhmo.cuanaku.model.Transaction

interface HistoryView {

    fun onSuccess(transactions: Transaction.Transactions, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)
}