package com.github.tarcv.orderme.app.ui.robots

import android.R
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

fun popUpMessage(popUpFunction: PopUpRobot.() -> Unit) = PopUpRobot().apply { popUpFunction() }

class PopUpRobot : BaseRobot() {
    private val alertTitleMatcher = withText("Success!")
    private val alertMessageMatcher = withId(R.id.message)
    private val alertOkButtonMatcher = withText("OK")
    private val wrongQrErrMsg = withText("QR Code is not compatible " +
            "with OrderMe (not a place code?)")
    private val successReservationMessage = withText("Your reservation was made")

    private val okButtonMatcher = withText("OK")

    private val successOrderMsg = withText("Your order is accepted!")

    fun getWrongQrErrorMessageText(): String = getElementText(wrongQrErrMsg)

    fun errorMessageIsDisplayed() = displayed(wrongQrErrMsg)

    fun orderSuccessMessageIsDisplayed() = displayed(successOrderMsg)

    fun successMessageIsDisplayed() = displayed(successReservationMessage)

    fun getAlertTitleText(): String = getElementText(alertTitleMatcher)

    fun getAlertMessageText(): String = getElementText(alertMessageMatcher)

    fun tapAlertOkButton() = tapBy(alertOkButtonMatcher)
}
