package com.bankaccenture.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bankaccenture.R
import com.bankaccenture.ui.recyclerview.adapter.HomeListaAdapter
import com.bankaccenture.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel by inject<HomeViewModel>()
    private val adapter by inject<HomeListaAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraReclyerView()
        buscarTrasacoes()
    }

    private fun configuraReclyerView() {
        home_recycler_view.adapter = adapter
    }

    private fun buscarTrasacoes() {
        TODO("mudar hardCode por id  de fato do usuario")
        viewModel.todasTrasacoes(1).observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.add(it)
            }
        })
    }
}