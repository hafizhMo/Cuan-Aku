package com.hafizhmo.cuanaku.ui.fragments.register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentRegisterBinding
import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.ui.BaseView
import com.hafizhmo.cuanaku.ui.activities.main.MainActivity
import com.hafizhmo.cuanaku.utils.CommonUtil
import com.hafizhmo.cuanaku.utils.SharedPref

class RegisterFragment : Fragment(), RegisterView, BaseView {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var presenter: RegisterPresenter
    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        presenter = RegisterPresenter(this)

        val roles = listOf("Si Wali", "Si Beban")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, roles)
        binding.roleInput.setAdapter(adapter)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            showLoading()
            var selectedRole = 2
            if (binding.roleInput.text.toString().equals("Si Wali")){
                selectedRole = 1
            }

            validate(
                binding.nameInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString(),
                selectedRole
            )
        }

        return binding.root
    }

    override fun showLoading() {
        hideLoading()
        progressDialog = CommonUtil.showLoadingDialog(context)
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog?.isShowing!!)
            progressDialog?.cancel()
    }

    override fun onSuccess(user: Auth.User, message: String) {
        SharedPref(requireContext()).saveSession(user)
        hideLoading()
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    override fun onFailed(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onAlreadyExists(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun validate(name: String, email: String, password: String, role: Int) {
        when {
            name.isEmpty() -> binding.emailInput.error = "This field must be fill!"
            email.isEmpty() -> binding.emailInput.error = "This field must be fill!"
            password.isEmpty() -> binding.passwordInput.error = "This field must be fill!"
            else -> presenter.register(name, email, password, role)
        }
    }

}