package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.ui.utils.getText
import java.util.concurrent.atomic.AtomicReference

fun callAWaiterOptions(CallAWaiterOptionsFunction: CallAWaiterOptionsRobot.() -> Unit) =
        CallAWaiterOptionsRobot().apply(CallAWaiterOptionsFunction)

class CallAWaiterOptionsRobot {
    private val bringAMenuMatcher = withText("Bring a menu")
    private val alertTitleMatcher = withText("Success!")
    private val alertMessageMatcher = withId(android.R.id.message)

    fun sleep() = Thread.sleep(2000)

    fun tapOnBringAMenu() {
        onView(bringAMenuMatcher)
                .perform(click())
    }

    fun getAlertTitleText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(alertTitleMatcher).perform(getText(textReference))
        return textReference.toString()
    }

    fun getAlertMessageText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(alertMessageMatcher).perform(getText(textReference))
        return textReference.toString()
    }
}