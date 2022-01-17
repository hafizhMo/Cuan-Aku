package com.hafizhmo.cuanaku.ui.activities.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ActivityProfileBinding
import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.ui.activities.auth.AuthActivity
import com.hafizhmo.cuanaku.utils.SharedPref

class ProfileActivity : AppCompatActivity(), ProfileView {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        presenter = ProfilePresenter(this)
        setContentView(binding.root)

        val pref = SharedPref(this)
        presenter.getResponse(pref.getSessionId(), pref.getSessionToken())

        binding.logoutButton.setOnClickListener {
            SharedPref(this).clearSession()
            startActivity(Intent(this, AuthActivity::class.java))
            finishAffinity()
        }
    }

    override fun onSuccess(user: Auth.User, msg: String) {
        binding.nameText.text = user.name
        binding.roleText.text = if (user.role.equals("1")) "Si Wali" else "Si Beban"
        Glide.with(this)
            .load(user.profile_photo_url)
            .placeholder(R.drawable.profile_placeholder)
            .into(binding.profileImage)
    }

    override fun onFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}