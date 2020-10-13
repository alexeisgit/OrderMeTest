package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withText

fun popUpMessage(popUpFunction: PopUpRobot.() -> Unit) = PopUpRobot().apply { popUpFunction() }

class PopUpRobot : BaseRobot() {

    private val wrongQrErrMsg = withText("QR Code is not compatible " +
            "with OrderMe (not a place code?)")

    fun getWrongQrErrorMessageText(): String = getElementText(wrongQrErrMsg)

    fun errorMessageIsDisplayed() = isDisplayed(wrongQrErrMsg)
}
