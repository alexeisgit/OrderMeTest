package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.menuDetails
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ShoppingCartTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)
    private val republiqueQRCode = "3_5"

    @Test
    fun verifyDefaultShoppingCartValue() {
        skipLogin()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
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

        menuDetails {
            sleep()
            assertEquals("0.0", verifyBucketValue())
        }
    }

    @Test
    fun verifyShoppingCartTotal() {
        skipLogin()

        restaurantList {
            tapQRBtn()
            sleep()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            sleep()
            tapSubmitButton()
        }

        restaurant {
            tapMenu()
            sleep()
        }

        menu {
            selectSaladsMenu()
            sleep()
        }

        menuDetails {
            addToCart("COLEMAN FARMS LITTLE GEMS")
            addToCart("BLACK & WHITE SALAD")
            addToCart("OCTOPUS")
            sleep()
            assertEquals("77.0", getShoppingCartValue())
        }
    }
}
