package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgetings

interface BudgetView {

    fun onSuccess(budget: Budgetings.Budget, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)
}