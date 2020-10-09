package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun detectTable(detectTableFunction: DetectTableRobot.() -> Unit) =
        DetectTableRobot().apply(detectTableFunction)

class DetectTableRobot {
    private val qrCodeEditTextMatcher = withId(R.id.qrCodeText)
    private val submitButtonMatcher = withId(R.id.submitButton)

    fun enterQrCode(qrCode: String) {
        onView(qrCodeEditTextMatcher)
                .perform(typeText(qrCode))
    }

    fun tapOnSubmit() {
        onView(submitButtonMatcher)
                .perform(click())
    }
}
