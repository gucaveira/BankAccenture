package com.bankaccenture.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        home_recycler_view.adapter = adapter
    }

}