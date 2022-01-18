package com.hafizhmo.cuanaku.ui.activities.invite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hafizhmo.cuanaku.databinding.ActivityInviteBinding
import com.hafizhmo.cuanaku.utils.SharedPref

class InviteActivity : AppCompatActivity(), InviteView {
    
    private lateinit var binding: ActivityInviteBinding
    private lateinit var presenter: InvitePresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteBinding.inflate(layoutInflater)
        presenter = InvitePresenter(this)
        setContentView(binding.root)

        val pref = SharedPref(this)
        val role = if (pref.getSessionRole() == "1") "wali" else "beban"

        binding.submitButton.setOnClickListener {
            if (validate()) {
                showLoading()
                presenter.invite(
                    role,
                    pref.getSessionId(),
                    binding.amountInput.text.toString().toInt(),
                    pref.getSessionToken()
                )
            }
        }
    }

    override fun inviteSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun inviteNotFound(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun inviteFailed(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.submitButton.isEnabled = false
        binding.submitButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.submitButton.isEnabled = true
        binding.submitButton.isEnabled = true
    }

    private fun validate(): Boolean {
        if (binding.amountInput.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Field category and amount is still empty!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}