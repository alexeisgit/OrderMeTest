package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webKeys
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator.ID
import androidx.test.espresso.web.webdriver.Locator.NAME
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

open class BaseRobot {

    fun sleep(timeout: Long = 2000) = Thread.sleep(timeout)

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

    fun typeInWebElementById(locator: String, text: String) = onWebView()
            .withElement(findElement(ID, locator))
            .perform(webKeys(text))

    fun tapOnWebElementByName(locator: String) = onWebView()
            .withElement(findElement(NAME, locator))
            .perform(webClick())
}
