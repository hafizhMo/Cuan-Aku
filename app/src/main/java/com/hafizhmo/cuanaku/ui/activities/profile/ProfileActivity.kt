package com.hafizhmo.cuanaku.ui.activities.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ActivityProfileBinding
import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.model.Feature
import com.hafizhmo.cuanaku.ui.activities.auth.AuthActivity
import com.hafizhmo.cuanaku.ui.activities.relation.RelationActivity
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

        val aname = resources.getStringArray(R.array.profile_account)
        val aimage = resources.obtainTypedArray(R.array.image_account)
        val akun: MutableList<Feature> = mutableListOf()

        akun.clear()
        for (i in aname.indices)
            akun.add(Feature(aname[i], aimage.getResourceId(i, 0)))
        aimage.recycle()

        val oname = resources.getStringArray(R.array.profile_other)
        val oimage = resources.obtainTypedArray(R.array.image_other)
        val other: MutableList<Feature> = mutableListOf()

        other.clear()
        for (i in oname.indices)
            other.add(Feature(oname[i], oimage.getResourceId(i, 0)))
        oimage.recycle()

        binding.recyclerAkun.apply {
            layoutManager = object : LinearLayoutManager(this@ProfileActivity) {
                override fun canScrollVertically(): Boolean = false
            }
            adapter = ProfileAdapter(this@ProfileActivity, akun) {
                when (it) {
                    0 -> { /* edit profile */
                    }
                    1 -> { /* edit password */
                    }
                    2 -> {
                        startActivity(Intent(this@ProfileActivity, RelationActivity::class.java))
                    }
                    3 -> { /* language */
                    }
                    4 -> { /* dark theme */
                    }
                }
            }
        }

        binding.recyclerOther.apply {
            layoutManager = object : LinearLayoutManager(this@ProfileActivity) {
                override fun canScrollVertically(): Boolean = false
            }
            adapter = ProfileAdapter(this@ProfileActivity, other) {
                when (it) {
                    0 -> { /* Privacy Policy */
                    }
                    1 -> { /* Terms of Services */
                    }
                    2 -> { /* about developer */
                    }
                    3 -> {
                        val packageName = "com.gojek.app"
                        val uri: Uri = Uri.parse("market://details?id=$packageName")
                        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(
                            Intent.FLAG_ACTIVITY_NO_HISTORY or
                                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                        )
                        try {
                            startActivity(goToMarket)
                        } catch (e: ActivityNotFoundException) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                                )
                            )
                        }
                    }
                }
            }
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