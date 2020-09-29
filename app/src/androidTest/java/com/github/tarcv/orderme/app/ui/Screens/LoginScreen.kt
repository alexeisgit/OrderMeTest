package com.github.tarcv.orderme.app.ui.Screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.github.tarcv.orderme.app.R

class LoginScreen {
    private val loginLaterButton = ViewMatchers.withId(R.id.login_later_button)

    fun loginLater() {
        onView(loginLaterButton).perform(ViewActions.click())
    }
}