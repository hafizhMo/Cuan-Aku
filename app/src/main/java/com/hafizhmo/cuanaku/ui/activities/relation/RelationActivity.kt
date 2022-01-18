package com.hafizhmo.cuanaku.ui.activities.relation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.ActivityRelationBinding
import com.hafizhmo.cuanaku.model.Relations
import com.hafizhmo.cuanaku.ui.activities.invite.InviteActivity
import com.hafizhmo.cuanaku.utils.SharedPref

class RelationActivity : AppCompatActivity(), RelationView {
    private lateinit var binding: ActivityRelationBinding
    private lateinit var presenter: RelationPresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationBinding.inflate(layoutInflater)
        presenter = RelationPresenter(this)
        setContentView(binding.root)

        binding.inviteButton.setOnClickListener {
            startActivity(Intent(this, InviteActivity::class.java))
        }

        binding.refreshLayout.setOnRefreshListener {
            showLoading()
            val pref = SharedPref(this)
            val role = if (pref.getSessionRole() == "1") "wali" else "beban"
            presenter.getRelations(role, pref.getSessionId(), pref.getSessionToken())
        }
    }

    override fun onStart() {
        super.onStart()
        val pref = SharedPref(this)
        val role = if (pref.getSessionRole() == "1") "wali" else "beban"
        presenter.getRelations(role, pref.getSessionId(), pref.getSessionToken())
    }

    override fun getAllSuccess(relations: List<Relations.Relations>, message: String) {
        hideLoading()
        binding.lottieView.visibility = View.GONE
        binding.lottieText.visibility = View.GONE

        binding.relationRecycler.layoutManager = LinearLayoutManager(this)
        binding.relationRecycler.adapter = RelationAdapter(this, relations)
    }

    override fun getAllEmpty(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
    }

    override fun getAllFailed(message: String) {
        hideLoading()
        binding.lottieText.visibility = View.VISIBLE
        binding.lottieText.text = message
        binding.lottieView.setAnimation(R.raw.idle)
        binding.lottieView.playAnimation()
    }

    override fun editSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editNotFound(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun editFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteNotFound(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun deleteFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.relationRecycler.visibility = View.GONE
        binding.lottieView.visibility = View.VISIBLE
        binding.lottieView.setAnimation(R.raw.loading)
        binding.lottieView.playAnimation()
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.relationRecycler.visibility = View.VISIBLE
        binding.refreshLayout.isRefreshing = false
    }
}