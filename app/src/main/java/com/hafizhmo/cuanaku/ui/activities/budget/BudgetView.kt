package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgetingss

interface BudgetView {

    fun onSuccess(budget: List<Budgetingss.Budget>, message: String)

    fun onEmpty(message: String)

    fun onFailed(message: String)
}