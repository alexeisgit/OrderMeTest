package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

fun menu(menuFunction: MenuRobot.() -> Unit) =
        MenuRobot().apply(menuFunction)

class MenuRobot : BaseRobot() {

    private val saladsAndVeggiesMenu: Matcher<View> = withText("SALADS AND VEGETABLES")

    fun selectSaladsMenu() = tapBy(saladsAndVeggiesMenu)

    private fun tapButton(name: String): ViewInteraction = onView(withText(name))
            .perform(click())

    fun selectFish() = tapButton("FISH")
}
