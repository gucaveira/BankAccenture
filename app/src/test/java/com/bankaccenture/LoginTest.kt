package com.bankaccenture

import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.ui.extensions.formataParaMoedaBrasileira
import com.bankaccenture.utils.AppUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class LoginTest {

    private val loginUsuarioComCpf = LoginUsuario(emailCpf = "77118435007", senha = "Test@1")
    private var contaUsuario = ContaUsuario().balance


    @Test
    fun deve_VerificarCpf_QuandoDigitadoPeloUsuario() {
        val validateCPF = AppUtils.validateCPF(loginUsuarioComCpf.emailCpf)
        assertEquals(true, validateCPF)
    }

    @Test
    fun deve_VerificarSenha_QuandoDigitadoPeloUsuario() {
        val validatePassword = AppUtils.validatePassword(loginUsuarioComCpf.senha)
        assertEquals(true, validatePassword)
    }

    @Test
    fun deve_VerificarFormatacaoMoeda_QuandoRecebidoDaApi() {
        contaUsuario = BigDecimal("120.00")
        val moedaBrasileira = contaUsuario?.formataParaMoedaBrasileira()

        assertEquals("R$ 120,00", moedaBrasileira)
    }
}