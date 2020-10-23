package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
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
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class OrderFlowTest : BaseTest() {

    @get: Rule
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val republiqueQRCode = "3_5"
    private val bucatiniPasta = "BUCATINI"
    private val englishPeaPasta = "ENGLISH PEA AGNOLOTTI"
    private val mafaldinePasta = "MAFALDINE"
    private val expectedTotal = "$91.0"
    private val expectedTotal2 = "$77.0"
    private val cookRanchPigsFeet = "COOK RANCH PIG'S FEET"
    private val sonomaDuckBreast = "SONOMA DUCK BREAST"
    private val beefShortRib = "BEEF SHORT RIB"
    private val primeBeefFilet = "PRIME BEEF FILET"
    private val tournedosRossini = "TOURNEDOS ROSSINI"
    val expectedTotalForMeatMenu = "$226.0"

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

    @Test
    fun verifyOrderFlowWithSaladsAndVegetables() {

        login {
            loginWithFacebook()
            sleep()
        }

        restaurantList {
            tapQRBtn()
            sleep()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
            sleep()
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
            openShoppingCart()
            sleep()
        }

        shoppingCart {
            inputAddress()
            selectAccept()
        }

        val expectedOrderTime = getCurrentTime()
        val expectedOderDate = getCurrentDate()

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
            isOrderDisplayed(
                    republiqueName,
                    expectedOderDate,
                    expectedOrderTime,
                    expectedTotal2)
        }
    }

    @Test
    fun verifyCompleteOrderFlowWithMeatMenu() {
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
            selectMeatMenu()
        }

        menuDetails {
            sleep()
            addToCart(cookRanchPigsFeet)
            addToCart(beefShortRib)
            addToCart(sonomaDuckBreast)
            addToCart(primeBeefFilet)
            addToCart(tournedosRossini)
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
            isOrderDisplayed(republiqueName, expectedOrderDate,
                    expectedOrderTime, expectedTotalForMeatMenu)
        }
    }
}
