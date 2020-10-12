package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.getText
import java.util.concurrent.atomic.AtomicReference

fun restaurant(restaurantFunction: RestaurantRobot.() -> Unit) =
        RestaurantRobot().apply(restaurantFunction)

class RestaurantRobot : BaseRobot() {
    private val restTitleMatcher = withId(R.id.restaurant_name)
    private val restOceanSeafoodImage = withId(R.id.restaurant_image_view)
    private val menuMatcher = withText("Menu")
    private val detectTableMatcher = withText("Detect table")
    private val callAWaiterMatcher = withText("Call a waiter")
    private val phoneMatcher = withText("Phone")
    private val locationMatcher = withText("Location")

    fun checkIfTitleIsDisplayed() = isDisplayed(restTitleMatcher)

    fun checkIfTextIsDisplayed(text: String) = isDisplayed(withText(text))

    fun checkIfButtonIsVisible(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))

    fun checkIfImageIsDisplayed() = onView(restOceanSeafoodImage)
            .check(matches(isDisplayed()))

    fun checkIfLocationBtnIsDisplayed() = isDisplayed(locationMatcher)

    fun getRestaurantTitleText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(restTitleMatcher).perform(getText(textReference))
        val actualText = textReference.toString()
        return actualText
    }

    fun selectButton(text: String) {
        onView(withText(text))
                .perform(click())
    }

    fun tapMenu() = tapBy(menuMatcher)

    fun tapOnDetectTable() {
        onView(detectTableMatcher)
                .perform(click())
    }

    fun tapOnCallAWaiter() {
        onView(callAWaiterMatcher)
                .perform(click())
    }

    fun tapOnPhone() = onView(phoneMatcher).perform(click())

    fun tapLocation() = tapBy(locationMatcher)
}
