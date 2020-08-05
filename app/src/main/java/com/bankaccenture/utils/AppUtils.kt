package com.bankaccenture.utils

import java.util.regex.Pattern

class AppUtils {

    companion object {

        fun validateCPF(cpf: String): Boolean {
            val cpfClean = cpf.replace(".", "").replace("-", "")

            if (cpfClean.length != 11)
                return false

            try {
                val number = cpfClean.toLong()
            } catch (e: Exception) {
                return false
            }

            var dvCurrent10 = cpfClean.substring(9, 10).toInt()
            var dvCurrent11 = cpfClean.substring(10, 11).toInt()

            val cpfNineFirst = IntArray(9)
            var i = 9
            while (i > 0) {
                cpfNineFirst[i - 1] = cpfClean.substring(i - 1, i).toInt()
                i--
            }
            var sumProductNine = IntArray(9)
            var weight = 10
            var position = 0
            while (weight >= 2) {
                sumProductNine[position] = weight * cpfNineFirst[position]
                weight--
                position++
            }

            var dvForTenthDigit = sumProductNine.sum() % 11
            dvForTenthDigit = 11 - dvForTenthDigit
            if (dvForTenthDigit > 9)
                dvForTenthDigit = 0
            if (dvForTenthDigit != dvCurrent10)
                return false

            var cpfTenFirst = cpfNineFirst.copyOf(10)
            cpfTenFirst[9] = dvCurrent10

            var sumProductTen = IntArray(10)
            var w = 11
            var p = 0
            while (w >= 2) {
                sumProductTen[p] = w * cpfTenFirst[p]
                w--
                p++
            }

            var dvForeleventhDigit = sumProductTen.sum() % 11
            dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
            if (dvForeleventhDigit > 9)
                dvForeleventhDigit = 0
            if (dvForeleventhDigit != dvCurrent11)
                return false

            return true
        }

        fun validateEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        fun validatePassword(pass: String): Boolean {
            val passRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#%!*:;?$\\.,%]).{4,20})"
            val pattern = Pattern.compile(passRegex)
            val matcherPass = pattern.matcher(pass)
            return matcherPass.matches()
        }
    }
}