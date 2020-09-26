package com.github.tarcv.orderme.app.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.Screens.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun verifyRestTitle() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        println("found a login later button and clicked on it")
        sleep(2000)

        onView(withText("Ocean Seafood")).perform(click())

        onView(withId(R.id.restaurant_name)).check(matches(isDisplayed()))
    }
}
