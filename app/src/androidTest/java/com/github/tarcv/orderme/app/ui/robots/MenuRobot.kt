package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText

fun menu(menuFunction: MenuRobot.() -> Unit) =
        MenuRobot().apply(menuFunction)

class MenuRobot {

    private fun tapButton(name: String): ViewInteraction = onView(withText(name))
            .perform(click())

    fun selectFish() = tapButton("FISH")
    fun sleep() = Thread.sleep(2000)
}