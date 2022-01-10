package com.hafizhmo.cuanaku.ui.fragments.login

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

class LoginFragment : Fragment(), LoginView {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity?.finish()
        }
        return binding.root
    }

    override fun onSuccess(user: Auth.User, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}