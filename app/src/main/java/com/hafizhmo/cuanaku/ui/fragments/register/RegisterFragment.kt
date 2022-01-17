package com.hafizhmo.cuanaku.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hafizhmo.cuanaku.R
import com.hafizhmo.cuanaku.databinding.FragmentRegisterBinding
import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.ui.activities.main.MainActivity
import com.hafizhmo.cuanaku.utils.SharedPref

class RegisterFragment : Fragment(), RegisterView {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var presenter: RegisterPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        presenter = RegisterPresenter(this)

        val roles = listOf("Si Wali", "Si Beban")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, roles)
        binding.roleInput.setAdapter(adapter)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            var selectedRole = 2
            if (binding.roleInput.text.toString().equals("Si Wali")) {
                selectedRole = 1
            }

            if (binding.nameInput.text.toString().isEmpty() ||
                binding.emailInput.text.toString().isEmpty() ||
                binding.passwordInput.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Name, email and password can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                showLoading()
                presenter.register(
                    binding.nameInput.text.toString(),
                    binding.emailInput.text.toString(),
                    binding.passwordInput.text.toString(),
                    selectedRole
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onAlreadyExists(message: String) {
        hideLoading()
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