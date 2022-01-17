package com.hafizhmo.cuanaku.ui.fragments.history

import com.hafizhmo.cuanaku.model.Transactions

interface HistoryView {

    fun onSuccess(transactions: List<Transactions.Transactions>, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)
}