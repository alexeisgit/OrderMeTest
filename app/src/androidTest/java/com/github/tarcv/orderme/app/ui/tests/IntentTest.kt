package com.github.tarcv.orderme.app.ui.tests

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class IntentTest : BaseTest() {

    @get:Rule
    val intentsTestRule = IntentsTestRule(SplashActivity::class.java)

    private val republiqueName = "Republique"
    private val burgerName = "Burger"
    private val republiguePhoneNumber = "+1 310-362-6115"
    private val burgerPhoneNumber = "5555555"
    private val oceanLocation = "34.062270,-118.239631"

    @Before
    fun blockExternalApps() {
        intending(not(isInternal())).respondWith(ActivityResult(RESULT_OK, null))
    }

    @Test
    fun testOpenPhone() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant(republiqueName)
        }
        restaurant {
            tapOnPhone()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_DIAL)),
                        hasData("tel:$republiguePhoneNumber")
                )
        )
    }

    @Test
    fun testOpenBurgerPhone() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant(burgerName)
        }
        restaurant {
            tapOnPhone()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_DIAL)),
                        hasData("tel:$burgerPhoneNumber")
                )
        )
    }

    @Test
    fun testOceanLocation() {
        skipLogin()

        restaurantList {
            selectRestaurant(oceanSeafoodName)
        }

        restaurant {
            tapLocation()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_VIEW)),
                        hasData("geo:$oceanLocation")
                )
        )
    }
}
