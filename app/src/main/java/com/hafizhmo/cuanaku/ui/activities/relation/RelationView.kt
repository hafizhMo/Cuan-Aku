package com.hafizhmo.cuanaku.ui.activities.relation

import com.hafizhmo.cuanaku.model.Relations

interface RelationView {

    fun getAllSuccess(relations: List<Relations.Relations>, message: String)

    fun getAllEmpty(message: String)

    fun getAllFailed(message: String)

    fun editSuccess(message: String)

    fun editNotFound(message: String)

    fun editFailed(message: String)

    fun deleteSuccess(message: String)

    fun deleteNotFound(message: String)

    fun deleteFailed(message: String)
}