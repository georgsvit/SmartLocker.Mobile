package com.example.smartlockermobile.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.smartlockermobile.R
import androidx.lifecycle.Observer
import com.example.smartlockermobile.data.network.ApiStatus
import com.example.smartlockermobile.databinding.FragmentLoginBinding
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val email = binding.loginInput
        val password = binding.passwordInput

        binding.loginViewModel = viewModel

        email.doAfterTextChanged {
            viewModel.loginDataChanged(email = email.text.toString(), password = password.text.toString())
        }

        password.doAfterTextChanged {
            viewModel.loginDataChanged(email = email.text.toString(), password = password.text.toString())
        }

        binding.loginBtn.setOnClickListener {
            viewModel.onLoginClick(email = email.text.toString(), password = password.text.toString())
        }

        viewModel.tokenDate.observe(viewLifecycleOwner, Observer {  date ->
            if (date != null && date > Calendar.getInstance().time) {
//                startActivity(Intent(context, HomeActivity::class.java))
                Toast.makeText(context, "Transition to Home", Toast.LENGTH_SHORT).show()
//                activity?.finish()
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer { newStatus ->
            when (newStatus) {
                ApiStatus.ERROR -> Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                ApiStatus.DONE -> {
//                    startActivity(Intent(context, HomeActivity::class.java))
                    Toast.makeText(context, "Transition to Home", Toast.LENGTH_SHORT).show()
//                    activity?.finish()
                }
                ApiStatus.LOADING -> Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(context, getString(R.string.processing), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            viewModel.loginFormState.observe(viewLifecycleOwner, Observer { formState ->
                binding.loginBtn.isEnabled = formState.isDataValid
                if (formState.emailError != null) {
                    email.error = getString(formState.emailError)
                }
                if (formState.passwordError != null) {
                    password.error = getString(formState.passwordError)
                }
            })
        })

        return binding.root
    }
}