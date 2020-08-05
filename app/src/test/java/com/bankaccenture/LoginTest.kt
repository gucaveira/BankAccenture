package com.bankaccenture

import com.bankaccenture.model.LoginUser
import com.bankaccenture.model.UserAccount
import com.bankaccenture.ui.extensions.formatForCoinBrazilian
import com.bankaccenture.utils.AppUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class LoginTest {

    private val loginUserWithCpf = LoginUser(emailCpf = "77118435007", password = "Test@1")
    private var userAccount = UserAccount().balance


    @Test
    fun deve_VerificarCpf_QuandoDigitadoPeloUsuario() {
        val validateCPF = AppUtils.validateCPF(loginUserWithCpf.emailCpf)
        assertEquals(true, validateCPF)
    }

    @Test
    fun deve_VerificarSenha_QuandoDigitadoPeloUsuario() {
        val validatePassword = AppUtils.validatePassword(loginUserWithCpf.password)
        assertEquals(true, validatePassword)
    }

    @Test
    fun deve_VerificarFormatacaoMoeda_QuandoRecebidoDaApi() {
        userAccount = BigDecimal("120.00")
        val moedaBrasileira = userAccount?.formatForCoinBrazilian()

        assertEquals("R$ 120,00", moedaBrasileira)
    }
}