package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

open class BaseRobot {

    fun sleep() = Thread.sleep(2000)

    fun tapBy(matcher: Matcher<View>) = onView(matcher).perform(click())

    fun isDisplayed(matcher: Matcher<View>) = onView(matcher).check(matches(isDisplayed()))

    fun enterText(matcher: Matcher<View>, text: String) = onView(matcher)
            .perform(typeText(text))
            .perform(closeSoftKeyboard())

    fun getElementText(elementMatcher: Matcher<View>): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(elementMatcher).perform(getText(textReference))
        return textReference.toString()
    }
}
