package com.hafizhmo.cuanaku.ui.activities.transaction

import com.hafizhmo.cuanaku.model.Category

interface TransactionView {

    fun submitSuccess(message: String)

    fun submitFailed(message: String)

    fun editSuccess(message: String)

    fun editNotFound(message: String)

    fun editFailed(message: String)

    fun deleteSuccess(message: String)

    fun deleteNotFound(message: String)

    fun deleteFailed(message: String)

    fun categorySuccess(categories: List<Category.Categories>, message: String)

    fun categoryEmpty(message: String)

    fun categoryFailed(message: String)
}