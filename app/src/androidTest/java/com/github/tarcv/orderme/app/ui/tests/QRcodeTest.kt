package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QRcodeTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun verifyDetectTableQRcodeError() {
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