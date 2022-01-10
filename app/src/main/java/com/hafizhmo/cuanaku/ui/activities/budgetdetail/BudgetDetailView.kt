package com.hafizhmo.cuanaku.ui.activities.budgetdetail

interface BudgetDetailView {

    fun createSuccess(message: String)

    fun createFailed(message: String)

    fun editSuccess(message: String)

    fun editNotFound(message: String)

    fun editFailed(message: String)

}