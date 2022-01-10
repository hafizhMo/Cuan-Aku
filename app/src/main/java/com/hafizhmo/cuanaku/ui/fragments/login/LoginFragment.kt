package com.hafizhmo.cuanaku.ui.fragments.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentLoginBinding
import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.ui.BaseView
import com.hafizhmo.cuanaku.ui.activities.main.MainActivity
import com.hafizhmo.cuanaku.utils.CommonUtil
import com.hafizhmo.cuanaku.utils.SharedPref

class LoginFragment : Fragment(), LoginView, BaseView {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var presenter: LoginPresenter
    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        presenter = LoginPresenter(this)

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginButton.setOnClickListener {
            showLoading()
            validate(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
        return binding.root
    }

    override fun onSuccess(user: Auth.User, token: String, message: String) {
        SharedPref(requireContext()).saveSession(user)
        hideLoading()
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    override fun onFailed(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun validate(email: String, password: String) {
        when {
            email.isEmpty() -> binding.emailInput.error = "This field must be fill!"
            password.isEmpty() -> binding.passwordInput.error = "This field must be fill!"
            else -> presenter.login(email, password)
        }
    }

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtil.showLoadingDialog(context)
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog?.isShowing!!)
            progressDialog?.cancel()
    }
}