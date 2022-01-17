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
import com.hafizhmo.cuanaku.ui.activities.main.MainActivity
import com.hafizhmo.cuanaku.utils.CommonUtil
import com.hafizhmo.cuanaku.utils.SharedPref

class LoginFragment : Fragment(), LoginView {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var presenter: LoginPresenter

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
            if (binding.emailInput.text.toString().isEmpty() ||
                binding.passwordInput.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Email and password can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                showLoading()
                presenter.login(
                    binding.emailInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
            }
        }
        return binding.root
    }

    override fun onSuccess(user: Auth.User, token: String, message: String) {
        SharedPref(requireContext()).saveSession(user.id, user.role, token)
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    override fun onFailed(message: String) {
        hideLoading()
        binding.passwordInput.text = null
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.loadProgress.visibility = View.VISIBLE
        binding.loginButton.isEnabled = false
        binding.registerButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.loadProgress.visibility = View.INVISIBLE
        binding.loginButton.isEnabled = true
        binding.registerButton.isEnabled = true
    }
}