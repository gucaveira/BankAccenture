package com.bankaccenture.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bankaccenture.R
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.utils.AppUtils
import com.bankaccenture.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraBotaoLogar()
    }

    private fun configuraBotaoLogar() {
        login_btn.setOnClickListener {
            limpaCampos()
            val emailCpf = login_usuario.editText?.text.toString()
            val senha = login_senha.editText?.text.toString()

            if (validaCampos(emailCpf, senha)) {
                configuraViewModel(emailCpf, senha)
            }
        }
    }

    private fun configuraViewModel(emailCpf: String, senha: String) {
        loginViewModel.login(LoginUsuario(emailCpf, senha))
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    vaiParaHomeFragment(it)
                }
            })
    }

    private fun vaiParaHomeFragment(it: ContaUsuario) {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(it)
        controller.navigate(directions)
    }

    private fun limpaCampos() {
        login_usuario.error = null
        login_senha.error = null
    }

    private fun validaCampos(emailCpf: String, senha: String): Boolean {
        var valido: Boolean

        if (emailCpf.isBlank()) {
            login_usuario.error = resources.getString(R.string.usuario_em_branco)
            valido = false
        } else if (AppUtils.validateCPF(emailCpf) || AppUtils.validateEmail(emailCpf)) {
            valido = true
        } else {
            login_usuario.error = resources.getString(R.string.usuario_invalido)
            valido = false
        }

        if (senha.isBlank()) {
            login_senha.error = resources.getString(R.string.senha_em_branco)
            valido = false
        } else if (!loginViewModel.isSenhaValida(senha)) {
            valido = false
            login_senha.error = resources.getString(R.string.senha_invalida)
        }
        return valido
    }
}