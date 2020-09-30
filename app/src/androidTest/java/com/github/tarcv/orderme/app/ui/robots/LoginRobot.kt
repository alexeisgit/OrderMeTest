package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun login(loginFunction: LoginRobot.() -> Unit) = LoginRobot().apply { loginFunction() }

class LoginRobot {
    private val loginLaterButton = withId(R.id.login_later_button)

    fun loginLater() {
        onView(loginLaterButton).perform(click())
    }

    fun sleep() = Thread.sleep(2000)
}
