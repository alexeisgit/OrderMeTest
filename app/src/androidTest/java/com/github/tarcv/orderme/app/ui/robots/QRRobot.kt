package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.R

fun qrScreen(qrFunction: QRRobot.() -> Unit) = QRRobot().apply { qrFunction() }

class QRRobot : BaseRobot() {
    private val qrCodeText = withId(R.id.qrCodeText)
    private val submitBtn = withId(R.id.submitButton)
    private val republiqueQRCode = "3_5"

    private fun enterQRCode(qrCode: String) = enterText(qrCodeText, qrCode)

    fun enterRepubliqueQRCode() = enterQRCode(republiqueQRCode)

    fun tapSubmitButton() = tapBy(submitBtn)

    fun tapOnEnterQRcodeButton(text: String) = onView(withText(text))
            .perform(click())

    fun checkIfErrorMessageIsVisible(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))
}
