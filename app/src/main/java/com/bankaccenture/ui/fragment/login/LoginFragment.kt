package com.bankaccenture.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bankaccenture.R
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraBotao()
    }

    private fun configuraBotao() {
        login_btn.setOnClickListener {
            limpaCampos()
            val emailCpf = login_usuario.editText?.text.toString()
            val senha = login_senha.editText?.text.toString()

            if (validaCampos(emailCpf, senha)) {
                loginViewModel.login(LoginUsuario(emailCpf, senha))
                    .observe(viewLifecycleOwner, Observer { resource ->
                        resource?.let {
                            val dado = it.dado
                            Toast.makeText(context, "${dado.name}", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }

    private fun limpaCampos() {
        login_usuario.error = null
        login_senha.error = null
    }

    private fun validaCampos(emailCpf: String, senha: String): Boolean {
        var valido = true

        if (emailCpf.isBlank()) {
            login_usuario.error = "Usuario inválido"
            valido = false
        }

        if (senha.isBlank()) {
            login_senha.error = "Senha inválida"
            valido = false
        }
        if (!isSenhaValida(senha)) {
            valido = false
            Toast.makeText(context, "Senha dever ter minimo 6 caractere", Toast.LENGTH_SHORT).show()
        }
        return valido
    }

    private fun isSenhaValida(password: String): Boolean {
        return password.length >= 6
    }
}