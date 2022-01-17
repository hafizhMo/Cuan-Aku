package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgetings

interface DashboardView {

    fun onSuccess(budget: Budgetings.Budget, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)

}