package com.github.tarcv.orderme.app.ui.Screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.github.tarcv.orderme.app.R

class RestaurantScreen {
    private val restTitleMatcher = withId(R.id.restaurant_name)

    fun checkIfTitleIsDisplayed() = onView(restTitleMatcher).check(matches(isDisplayed()))

    fun checkIfTextIsDisplayed(text: String) = onView(withText(text)).check(matches(isDisplayed()))
}