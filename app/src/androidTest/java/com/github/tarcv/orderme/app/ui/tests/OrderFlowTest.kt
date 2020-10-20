package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.bottomNav
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.menuDetails
import com.github.tarcv.orderme.app.ui.robots.ordersList
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.shoppingCart
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class OrderFlowTest : BaseTest() {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    private val republiqueQRCode = "3_5"
    private val bucatiniPasta = "BUCATINI"
    private val englishPeaPasta = "ENGLISH PEA AGNOLOTTI"
    private val mafaldinePasta = "MAFALDINE"
    val expectedTotal = "$91.0"

    @Test
    fun verifyCompleteOrderFlowWithPasta() {
        login {
            loginWithFacebook()
            sleep()
        }

        restaurantList {
            sleep()
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            sleep()
            tapMenu()
        }

        menu {
            sleep()
            selectPastaMenu()
        }

        menuDetails {
            sleep()
            addToCart(bucatiniPasta)
            addToCart(englishPeaPasta)
            addToCart(mafaldinePasta)
            openShoppingCart()
            sleep()
        }

        shoppingCart {
            inputAddress()
            selectAccept()
        }

        val expectedOrderDate = getCurrentDate()
        val expectedOrderTime = getCurrentTime()

        popUpMessage {
            sleep()
            orderSuccessMessageIsDisplayed()
            tapAlertOkButton()
        }

        bottomNav {
            sleep()
            tapOrdersTab()
        }

        ordersList {
            sleep()
            isOrderDisplayed(republiqueName, expectedOrderDate, expectedOrderTime, expectedTotal)
        }
    }
}
