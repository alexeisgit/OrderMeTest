package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class QRcodeTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    @Test
    fun verifyDetectTableQRcodeError() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(beautyEssexName)
        }

        restaurant {
            tapOnDetectTable()
        }

        qrScreen {
            tapOnEnterQRcodeButton("Simulate error")
            checkIfErrorMessageIsVisible("QR Code could not be scanned")
        }
    }
}