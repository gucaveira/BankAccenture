package com.bankaccenture.di

import com.bankaccenture.repository.HomeRepository
import com.bankaccenture.repository.LoginRepository
import com.bankaccenture.retrofit.webclient.WebClient
import com.bankaccenture.ui.fragment.login.LoginFragment
import com.bankaccenture.ui.recyclerview.adapter.HomeRecyclerAdapter
import com.bankaccenture.viewmodel.HomeViewModel
import com.bankaccenture.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory<LoginFragment> { LoginFragment() }
    factory<HomeRecyclerAdapter> { HomeRecyclerAdapter() }
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<HomeViewModel> { HomeViewModel(get()) }
}

val daoModule = module {
    single<LoginRepository> { LoginRepository(get()) }
    single<WebClient> { WebClient() }
    single<HomeRepository> { HomeRepository(get()) }
}