package com.github.tarcv.orderme.app.ui.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText

class RestaurantListScreen {

    fun selectRestaurant(name: String) {
        onView(withText(name)).perform(click())
    }
}
