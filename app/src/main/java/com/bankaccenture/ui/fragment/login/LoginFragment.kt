package com.bankaccenture.ui.fragment.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bankaccenture.R
import com.bankaccenture.model.LoginUser
import com.bankaccenture.model.UserAccount
import com.bankaccenture.utils.AppUtils
import com.bankaccenture.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val KEY_EMAILCPF = "emailCpf"
private const val KEY_PASSWORD = "password"

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val controller by lazy { findNavController() }
    private val sharedPreferences by lazy {
        context?.getSharedPreferences("preferencia", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setterButtonLogin()
        searchUserAndPasswordSaved()
    }

    private fun setterButtonLogin() {
        login_btn.setOnClickListener {
            clearFields()
            val emailCpf = login_user.editText?.text.toString()
            val password = login_password.editText?.text.toString()

            if (validateFields(emailCpf, password)) {
                sharedPreferenceConf(emailCpf, password)
                setterViewModel(emailCpf, password)
            }
        }
    }

    private fun searchUserAndPasswordSaved() {
        sharedPreferences?.let {
            login_user.editText?.setText(it.getString(KEY_EMAILCPF, ""))
            login_password.editText?.setText(it.getString(KEY_PASSWORD, ""))
        }
    }

    private fun sharedPreferenceConf(emailCpf: String, password: String) {
        sharedPreferences?.let {
            val ed: SharedPreferences.Editor = it.edit()
            ed.putString(KEY_EMAILCPF, emailCpf)
            ed.putString(KEY_PASSWORD, password)
            ed.apply()
        }
    }

    private fun setterViewModel(emailCpf: String, password: String) {
        loginViewModel.login(LoginUser(emailCpf, password))
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    vaiParaHomeFragment(it)
                }
            })
    }

    private fun vaiParaHomeFragment(it: UserAccount) {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(it)
        controller.navigate(directions)
    }

    private fun clearFields() {
        login_user.error = null
        login_password.error = null
    }

    private fun validateFields(emailCpf: String, password: String): Boolean {
        var valid: Boolean

        if (emailCpf.isBlank()) {
            login_user.error = resources.getString(R.string.user_black)
            valid = false
        } else if (AppUtils.validateCPF(emailCpf) || AppUtils.validateEmail(emailCpf)) {
            valid = true
        } else {
            login_user.error = resources.getString(R.string.user_invalid)
            valid = false
        }

        if (password.isBlank()) {
            login_password.error = resources.getString(R.string.password_blank)
            valid = false
        } else if (!loginViewModel.isPasswordValid(password)) {
            valid = false
            login_password.error = resources.getString(R.string.password_invalid)
        }
        return valid
    }
}