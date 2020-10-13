package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantSearchTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun restaurantSearch() {
        skipLogin()
        restaurantList {
            searchRestaurantName("Romanov")
            sleep()
            assertEquals(getRestaurantTitleText(), "Romanov")
            assertNotEquals(getRestaurantTitleText(), "Hakkasan")
            checkNumberOfRestaurants(1)
        }
    }
}
