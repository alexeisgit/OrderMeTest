package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class LoginTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    private val numberOfRestaurants = 6

    @Test
    fun verifyLoginWithFacebook() {

        login {
            tapLoginButton()
            sleep(7000)
        }

        loginWithFacebook()

        restaurantList {
            checkNumberOfRestaurants(numberOfRestaurants)
        }
    }
}