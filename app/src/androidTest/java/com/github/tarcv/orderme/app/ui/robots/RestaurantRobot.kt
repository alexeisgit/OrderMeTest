package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.R

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
    private val reservationMatcher = withText("Reservation")

    fun checkIfTitleIsDisplayed() = isDisplayed(restTitleMatcher)

    fun checkIfTextIsDisplayed(text: String) = isDisplayed(withText(text))

    fun checkIfButtonIsVisible(text: String) = onView(withText(text))
            .check(matches(isDisplayed()))

    fun checkIfImageIsDisplayed() = onView(restOceanSeafoodImage)
            .check(matches(isDisplayed()))

    fun checkIfLocationBtnIsDisplayed() = isDisplayed(locationMatcher)

    fun getRestaurantTitleText(): String = getElementText(restTitleMatcher)

    fun tapMenu() = tapBy(menuMatcher)

    fun tapOnDetectTable() = tapBy(detectTableMatcher)

    fun tapOnCallAWaiter() = tapBy(callAWaiterMatcher)

    fun tapOnPhone() = onView(phoneMatcher).perform(click())

    fun tapLocation() = tapBy(locationMatcher)

    fun tapReservation() = tapBy(reservationMatcher)
}
