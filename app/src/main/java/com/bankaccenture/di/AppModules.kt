package com.bankaccenture.di

import com.bankaccenture.viewmodel.HomeViewModel
import com.bankaccenture.repository.LoginRepository
import com.bankaccenture.retrofit.webclient.WebClient
import com.bankaccenture.ui.fragment.login.LoginFragment
import com.bankaccenture.ui.recyclerview.adapter.HomeListaAdapter
import com.bankaccenture.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory<LoginFragment> { LoginFragment() }
    factory<HomeListaAdapter> { HomeListaAdapter(get()) }
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<HomeViewModel> { HomeViewModel() }
}

val daoModule = module {
    single<LoginRepository> { LoginRepository(get()) }
    single<WebClient> { WebClient() }
}