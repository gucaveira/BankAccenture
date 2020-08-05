package com.bankaccenture.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bankaccenture.R
import com.bankaccenture.ui.extensions.formatAgency
import com.bankaccenture.ui.extensions.formataParaMoedaBrasileira
import com.bankaccenture.ui.recyclerview.adapter.HomeListaAdapter
import com.bankaccenture.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel by inject<HomeViewModel>()
    private val adapter by inject<HomeListaAdapter>()
    private val arguments by navArgs<HomeFragmentArgs>()
    private val contaUsuario by lazy { arguments.usuario }
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        esconderTeclado()
        configuraReclyerView()
        buscarTrasacoes()
        instanciaCampos()
        configuraBotaoLogout()
    }

    private fun esconderTeclado() {
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(getView()?.windowToken, 0)
    }

    private fun configuraReclyerView() {
        home_recycler_view.adapter = adapter
    }

    private fun buscarTrasacoes() {
        contaUsuario.userId?.let { id ->
            viewModel.todasTrasacoes(id).observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.add(it)
                }
            })
        }
    }

    private fun instanciaCampos() {
        home_textview_usuario_nome.text = contaUsuario.name
        home_textview_usuario_conta.text =
            contaUsuario.bankAccount + "/ " + contaUsuario.agency?.formatAgency()
        home_textview_usuario_balanco.text = contaUsuario.balance?.formataParaMoedaBrasileira()
    }

    private fun configuraBotaoLogout() {
        home_bt_logout.setOnClickListener {
            val directions =
                HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            controller.navigate(directions)
        }
    }
}
