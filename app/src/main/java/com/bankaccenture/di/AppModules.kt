package com.bankaccenture.di

import com.bankaccenture.ui.fragment.login.LoginFragment
import com.bankaccenture.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory<LoginFragment> { LoginFragment() }
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel() }
}