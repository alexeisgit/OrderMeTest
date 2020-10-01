package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun restaurant(restaurantFunction: RestaurantRobot.() -> Unit) =
        RestaurantRobot().apply(restaurantFunction)

class RestaurantRobot {
    private val restTitleMatcher = withId(R.id.restaurant_name)
    private val restOceanSeafoodImage = withId(R.id.restaurant_image_view)

    fun checkIfTitleIsDisplayed() = onView(restTitleMatcher)
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfTextIsDisplayed(text: String) = onView(ViewMatchers.withText(text))
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfButtonIsVisible(text: String) = onView(ViewMatchers.withText(text))
            .check(matches(ViewMatchers.isDisplayed()))

    fun checkIfImageIsDisplayed() = onView(restOceanSeafoodImage)
            .check(matches(ViewMatchers.isDisplayed()))
}