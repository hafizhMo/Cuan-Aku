package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgeting

interface DashboardView {

    fun onSuccess(budget: Budgeting.Budget, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)

}