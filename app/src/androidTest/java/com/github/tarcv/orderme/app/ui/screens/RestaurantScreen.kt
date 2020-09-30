package com.github.tarcv.orderme.app.ui.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R

class RestaurantScreen {
    private val restTitleMatcher = withId(R.id.restaurant_name)

    fun checkIfTitleIsDisplayed() = onView(restTitleMatcher).check(matches(isDisplayed()))

    fun checkIfTextIsDisplayed(text: String) = onView(withText(text)).check(matches(isDisplayed()))

    fun checkIfButtonIsVisible(text: String) = onView(withText(text)).check(matches(isDisplayed()))
}
