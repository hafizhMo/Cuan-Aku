package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.model.TransactionsGroup

interface DashboardView {

    fun onSuccess(budget: Budgetings.Budget, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)

    fun onGroupSuccess(transactions: List<TransactionsGroup.Transactions>, msg: String)

    fun onGroupFailed(msg: String)

}