package com.github.tarcv.orderme.app.ui.tests

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RestaurantListTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    private val numberOfRestaurants = 6

    @Test
    fun checkNumberOfRestaurants() {
        skipLogin()

        restaurantList {
            checkNumberOfRestaurants(numberOfRestaurants)
        }
    }
}