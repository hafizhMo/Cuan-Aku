package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgeting

interface BudgetView {

    fun onSuccess(budget: Budgeting.Budget, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)
}