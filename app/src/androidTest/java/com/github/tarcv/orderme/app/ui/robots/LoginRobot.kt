package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun login(loginFunction: LoginRobot.() -> Unit) = LoginRobot().apply { loginFunction() }

class LoginRobot : BaseRobot() {
    private val loginLaterButton = withId(R.id.login_later_button)
    private val loginFacebookButton = withId(R.id.login_button)

    fun loginLater() = tapBy(loginLaterButton)
    fun tapLoginButton() = tapBy(loginFacebookButton)
}
