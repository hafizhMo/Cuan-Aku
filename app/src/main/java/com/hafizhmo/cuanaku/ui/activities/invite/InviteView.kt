package com.hafizhmo.cuanaku.ui.activities.invite

interface InviteView {

    fun inviteSuccess(message: String)

    fun inviteNotFound(message: String)

    fun inviteFailed(message: String)

}