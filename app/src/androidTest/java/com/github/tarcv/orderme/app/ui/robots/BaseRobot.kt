package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

open class BaseRobot {

    fun sleep() = Thread.sleep(2000)

    fun tapBy(matcher: Matcher<View>) = onView(matcher).perform(click())

    fun isDisplayed(matcher: Matcher<View>) = onView(matcher).check(matches(isDisplayed()))
}
