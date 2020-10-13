package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

fun callAWaiterOptions(CallAWaiterOptionsFunction: CallAWaiterOptionsRobot.() -> Unit) =
        CallAWaiterOptionsRobot().apply(CallAWaiterOptionsFunction)

class CallAWaiterOptionsRobot : BaseRobot() {
    private val bringAMenuMatcher = withText("Bring a menu")
    private val alertTitleMatcher = withText("Success!")
    private val alertMessageMatcher = withId(android.R.id.message)

    fun tapOnBringAMenu() = tapBy(bringAMenuMatcher)

    fun getAlertTitleText(): String = getElementText(alertTitleMatcher)

    fun getAlertMessageText(): String = getElementText(alertMessageMatcher)
}