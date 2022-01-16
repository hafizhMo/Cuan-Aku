package com.hafizhmo.cuanaku.ui.activities.invite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafizhmo.cuanaku.databinding.ActivityInviteBinding

class InviteActivity : AppCompatActivity(), InviteView {
    
    private lateinit var binding: ActivityInviteBinding
    private lateinit var presenter: InvitePresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteBinding.inflate(layoutInflater)
        presenter = InvitePresenter(this)
        setContentView(binding.root)
    }

    override fun inviteSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun inviteNotFound(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun inviteFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}