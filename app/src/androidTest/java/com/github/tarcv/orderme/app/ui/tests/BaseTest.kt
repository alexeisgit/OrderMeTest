package com.github.tarcv.orderme.app.ui.tests

import com.github.tarcv.orderme.app.ui.robots.login

open class BaseTest {

    fun skipLogin() {
        login {
            loginLater()
            sleep()
        }
    }

    val oceanSeafoodName = "Ocean Seafood"
    val hakkasanName = "Hakkasan"
    val republiqueName = "Republique"
    val romanovName = "Romanov"
}
