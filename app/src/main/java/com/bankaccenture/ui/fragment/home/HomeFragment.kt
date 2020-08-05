package com.bankaccenture.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bankaccenture.R
import com.bankaccenture.ui.extensions.formatAgency
import com.bankaccenture.ui.extensions.formatForCoinBrazilian
import com.bankaccenture.ui.recyclerview.adapter.HomeRecyclerAdapter
import com.bankaccenture.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {

    private val viewModel by inject<HomeViewModel>()
    private val adapter by inject<HomeRecyclerAdapter>()
    private val arguments by navArgs<HomeFragmentArgs>()
    private val userAccount by lazy { arguments.user }
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setterRecyclerView()
        searchTransactions()
        setterFields()
        setterButtonLogout()
    }

    private fun setterRecyclerView() {
        home_recycler_view.adapter = adapter
    }

    private fun searchTransactions() {
        userAccount.userId?.let { id ->
            viewModel.allTransaction(id).observe(viewLifecycleOwner, Observer {
                it?.let { listTransactionsNotNull ->
                    adapter.add(listTransactionsNotNull)
                }
            })
        }
    }

    private fun setterFields() {
        home_textview_user_name.text = userAccount.name
        home_textview_user_account.text =
            userAccount.bankAccount + "/ " + userAccount.agency?.formatAgency()
        home_textview_balance.text = userAccount.balance?.formatForCoinBrazilian()
    }

    private fun setterButtonLogout() {
        home_bt_logout.setOnClickListener {
            val prefsEditor = context?.getSharedPreferences("preferencia", 0)?.edit()
            prefsEditor?.clear()
            prefsEditor?.apply()

            val directions =
                HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            controller.navigate(directions)
        }
    }
}
