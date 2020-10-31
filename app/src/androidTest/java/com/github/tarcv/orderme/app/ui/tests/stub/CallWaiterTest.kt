package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.filters.LargeTest
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.callAWaiterOptions
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CallWaiterTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val republiqueQRCode = "3_5"

    @Test
    fun verifyRepubliqueBringAMenuSuccess() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(republiqueName)
        }

        restaurant {
            tapOnDetectTable()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            tapOnCallAWaiter()
            postWaiter()
        }

        callAWaiterOptions {
            tapOnBringAMenu()
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Waiter is on his way",
                    getAlertMessageText())
        }
    }
}