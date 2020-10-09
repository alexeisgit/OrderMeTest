package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
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
    private val menuButton = "Menu"

    fun checkIfTitleIsDisplayed() = onView(restTitleMatcher)
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfTextIsDisplayed(text: String) = onView(ViewMatchers.withText(text))
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfButtonIsVisible(text: String) = onView(ViewMatchers.withText(text))
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfImageIsDisplayed() = onView(restOceanSeafoodImage)
            .check(matches(ViewMatchers.isDisplayed()))

    fun getRestaurantTitleText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(restTitleMatcher).perform(getText(textReference))
        val actualText = textReference.toString()
        return actualText
    }

    private fun tapButton(text: String) = onView(withText(text)).perform(click())

    fun tapMenu() = tapButton(menuButton)

    fun sleep() = Thread.sleep(2000)
}