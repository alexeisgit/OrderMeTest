package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R

fun qrScreen(qrFunction: QRRobot.() -> Unit) = QRRobot().apply { qrFunction() }

class QRRobot : BaseRobot() {
    private val qrCodeText = withId(R.id.qrCodeText)
    private val submitBtn = withId(R.id.submitButton)
    private val republiqueQRCode = "3_5"

    fun enterRepubliqueQRCode() = enterText(qrCodeText, republiqueQRCode)

    fun tapSubmitButton() = tapBy(submitBtn)

    fun tapOnEnterQRcodeButton(text: String) = tapBy(withText(text))

    fun checkIfErrorMessageIsVisible(text: String) = isDisplayed(withText(text))
}
