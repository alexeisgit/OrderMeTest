package com.github.tarcv.orderme.app.ui.tests.stub

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.After
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class IntentTest : BaseTest() {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(intentsTestRule)

    private val republiguePhoneNumber = "+1 310-362-6115"
    private val republiqueLocation = "34.064198,-118.343863"
    private val burgerPhoneNumber = "5555555"
    private val beautyEssexPhoneNumber = "+1 323 676-8880"
    private val hakkasanPhoneNumber = "+1 415-829-8148"
    private val oceanLocation = "34.062270,-118.239631"
    private val oceanSeafoodPhoneNumber = "+1 213-687-3088"
    private val burgerLocation = "12.3123,23.1312"

    @Before
    fun setupIntent() {
        init()
        intending(not(isInternal())).respondWith(ActivityResult(RESULT_OK, null))
    }

    @After
    fun teardownIntent() {
        release()
    }

    @Test
    fun testOpenPhone() {
        getPlaces("places.json", always = true)

        login {
            loginLater()
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
        getPlaces("places.json", always = true)

        login {
            loginLater()
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
    fun testOpenBeautyEssexPhone() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(beautyEssexName)
        }
        restaurant {
            tapOnPhone()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_DIAL)),
                        hasData("tel:$beautyEssexPhoneNumber")
                )
        )
    }

    @Test
    fun testOpenHakkasanPhone() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(hakkasanName)
        }
        restaurant {
            tapOnPhone()

            intended(
                    allOf(
                            hasAction(equalTo(ACTION_DIAL)),
                            hasData("tel:$hakkasanPhoneNumber")
                    )
            )
        }
    }

    @Test
    fun testOceanLocation() {
        getPlaces("places.json", always = true)

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

    @Test
    fun testRepubliqueLocation() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(republiqueName)
        }

        restaurant {
            tapLocation()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_VIEW)),
                        hasData("geo:$republiqueLocation")
                )
        )
    }

    @Test
    fun testOpenSeafoodPhoneNumber() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(oceanSeafoodName)
        }

        restaurant {
            tapOnPhone()

            intended(
                    allOf(
                            hasAction(equalTo(ACTION_DIAL)),
                            hasData("tel:$oceanSeafoodPhoneNumber")
                    )
            )
        }
    }
    @Test
    fun testBurgerLocation() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(burgerName)
        }

        restaurant {
            tapLocation()
        }

        intended(
                allOf(
                        hasAction(equalTo(ACTION_VIEW)),
                        hasData("geo:$burgerLocation")
                )
        )
    }
}
