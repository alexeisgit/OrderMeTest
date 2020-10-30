package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.contrib.PickerActions.setTime
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.DriverAtoms.webKeys
import com.github.tarcv.orderme.app.ui.utils.getItemCount
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator.ID
import androidx.test.espresso.web.webdriver.Locator.NAME
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference
import com.github.tarcv.orderme.app.R

open class BaseRobot {
    private val reservationsTab = withId(R.id.navigation_reservation)

    fun tapReservationsTab() = tapBy(reservationsTab)

    fun tapBy(matcher: Matcher<View>) = onView(matcher).perform(click())

    fun displayed(matcher: Matcher<View>) = onView(matcher).check(matches(isDisplayed()))

    fun enterText(matcher: Matcher<View>, text: String) = onView(matcher)
            .perform(typeText(text))
            .perform(closeSoftKeyboard())

    fun getElementText(elementMatcher: Matcher<View>): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(elementMatcher).perform(getText(textReference))
        return textReference.toString()
    }

    fun selectDate(day: Int, month: Int, year: Int) =
            onView(isAssignableFrom(DatePicker::class.java))
                    .perform(setDate(year, month, day))

    fun selectTime(hour: Int, min: Int) =
            onView(isAssignableFrom(TimePicker::class.java))
                    .perform(setTime(hour, min))

    fun typeInWebElementById(locator: String, text: String) = onWebView()
            .withElement(findElement(ID, locator))
            .perform(webKeys(text))

    fun tapOnWebElementByName(locator: String) = onWebView()
            .withElement(findElement(NAME, locator))
            .perform(webClick())

    fun getRecyclerViewItemsCount(elementMatcher: Matcher<View>): Int {
        val itemCount: AtomicReference<Int> = AtomicReference()
        onView(elementMatcher).perform(getItemCount(itemCount))
        return itemCount.toString().toInt()
    }

    fun scrollToLastItemOfRecyclerView(recylerViewMatcher: Matcher<View>) =
        onView(recylerViewMatcher)
            .perform(scrollToPosition<RecyclerView.ViewHolder>
            (getRecyclerViewItemsCount(recylerViewMatcher) - 1))
}
