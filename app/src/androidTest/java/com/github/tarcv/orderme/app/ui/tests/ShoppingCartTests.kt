package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.menuDetail
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.restaurant
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@LargeTest
@RunWith(AndroidJUnit4::class)
class ShoppingCartTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun verifyDefaultShoppingCartValue() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterRepubliqueQRCode()
            sleep()
            tapSubmitButton()
        }

        restaurant {
            sleep()
            tapMenu()
        }

        menu {
            sleep()
            selectFish()
        }

        menuDetail {
            sleep()
            assertEquals("0.0", verifyBucketValue())
        }
    }
}
