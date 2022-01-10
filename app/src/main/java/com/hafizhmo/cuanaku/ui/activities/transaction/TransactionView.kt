package com.hafizhmo.cuanaku.ui.activities.transaction

interface TransactionView {

    fun submitSuccess(message: String)

    fun submitFailed(message: String)

    fun editSuccess(message: String)

    fun editNotFound(message: String)

    fun editFailed(message: String)

    fun deleteSuccess(message: String)

    fun deleteNotFound(message: String)

    fun deleteFailed(message: String
    )
}