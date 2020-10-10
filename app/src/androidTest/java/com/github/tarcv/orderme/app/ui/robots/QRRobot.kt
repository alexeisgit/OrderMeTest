package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.R

fun qrScreen(qrFunction: QRRobot.() -> Unit) = QRRobot().apply { qrFunction() }

class QRRobot {
    private val qrCodeText = withId(R.id.qrCodeText)
    private val submitBtn = withId(R.id.submitButton)
    private val republiqueQRCode = "3_5"

    private fun enterQRCode(qrCode: String) = onView(qrCodeText)
        .perform(typeText(qrCode))
        .perform(closeSoftKeyboard())

    fun enterRepubliqueQRCode() = enterQRCode(republiqueQRCode)

    fun tapSubmitButton() = onView(submitBtn).perform(click())

    fun sleep() = Thread.sleep(2000)

    fun tapOnEnterQRcodeButton(text: String) = onView(withText(text))
            .perform(click())

    fun checkIfErrorMessageIsVisible(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))
}
