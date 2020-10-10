package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.getText
import java.util.concurrent.atomic.AtomicReference

fun restaurant(restaurantFunction: RestaurantRobot.() -> Unit) =
        RestaurantRobot().apply(restaurantFunction)

class RestaurantRobot {
    private val restTitleMatcher = withId(R.id.restaurant_name)
    private val restOceanSeafoodImage = withId(R.id.restaurant_image_view)
    private val menuMatcher = withText("Menu")
    private val detectTableMatcher = withText("Detect table")
    private val callAWaiterMatcher = withText("Call a waiter")

    fun checkIfTitleIsDisplayed() = onView(restTitleMatcher)
            .check(matches(isDisplayed()))

    fun checkIfTextIsDisplayed(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))

    fun checkIfButtonIsVisible(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))

    fun checkIfImageIsDisplayed() = onView(restOceanSeafoodImage)
            .check(matches(isDisplayed()))

    fun getRestaurantTitleText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(restTitleMatcher).perform(getText(textReference))
        val actualText = textReference.toString()
        return actualText
    }

    fun tapMenu() = onView(menuMatcher).perform(click())

    fun tapOnDetectTable() {
        onView(detectTableMatcher)
                .perform(click())
    }

    fun tapOnCallAWaiter() {
        onView(callAWaiterMatcher)
                .perform(click())
    }

    fun sleep() = Thread.sleep(2000)
}