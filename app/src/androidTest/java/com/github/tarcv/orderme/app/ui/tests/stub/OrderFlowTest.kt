package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.bottomNav
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.menuDetails
import com.github.tarcv.orderme.app.ui.robots.ordersList
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.shoppingCart
import com.github.tarcv.orderme.app.ui.tests.BaseTest
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
    private val republiqueId = 3
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
    private val expectedTotalForMeatMenu = "$226.0"
    private val expectedDate = "2020-10-18"
    private val expectedTime = "23:47"

    @Test
    fun verifyCompleteOrderFlowWithPasta() {
        getMenu(republiqueId, "menuRepublique.json", always = true)
        getOrders("menuOrders.json")
        postOrder("menuOrderPasta.json")

        loginWithFacebookMock()
        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            tapMenu()
        }

        menu {
            selectPastaMenu()
        }

        menuDetails {
            addToCart(bucatiniPasta)
            addToCart(englishPeaPasta)
            addToCart(mafaldinePasta)
            openShoppingCart()
        }

        shoppingCart {
            inputAddress()
            selectAccept()
        }

        popUpMessage {
            orderSuccessMessageIsDisplayed()
            tapAlertOkButton()
        }

        bottomNav {
            tapOrdersTab()
        }

        ordersList {
            isOrderDisplayed(republiqueName, expectedDate, expectedTime, expectedTotal)
        }
    }

    @Test
    fun verifyOrderFlowWithSaladsAndVegetables() {
        getMenu(republiqueId, "menuRepublique.json", always = true)
        getOrders("menuOrders.json")
        postOrder("menuOrderSalads.json")

        loginWithFacebookMock()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            tapMenu()
        }

        menu {
            selectSaladsMenu()
        }

        menuDetails {
            addToCart("COLEMAN FARMS LITTLE GEMS")
            addToCart("BLACK & WHITE SALAD")
            addToCart("OCTOPUS")
            openShoppingCart()
        }

        shoppingCart {
            inputAddress()
            selectAccept()
        }

        popUpMessage {
            orderSuccessMessageIsDisplayed()
            tapAlertOkButton()
        }

        bottomNav {
            tapOrdersTab()
        }

        ordersList {
            isOrderDisplayed(
                    republiqueName,
                    expectedDate,
                    expectedTime,
                    expectedTotal2)
        }
    }

    @Test
    fun verifyCompleteOrderFlowWithMeatMenu() {
        getMenu(republiqueId, "menuRepublique.json", always = true)
        getOrders("menuOrders.json")
        postOrder("menuOrderMeat.json")

        loginWithFacebookMock()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            tapMenu()
        }

        menu {
            selectMeatMenu()
        }

        menuDetails {
            addToCart(cookRanchPigsFeet)
            addToCart(beefShortRib)
            addToCart(sonomaDuckBreast)
            addToCart(primeBeefFilet)
            addToCart(tournedosRossini)
            openShoppingCart()
        }

        shoppingCart {
            inputAddress()
            selectAccept()
        }

        popUpMessage {
            orderSuccessMessageIsDisplayed()
            tapAlertOkButton()
        }

        bottomNav {
            tapOrdersTab()
        }

        ordersList {
            isOrderDisplayed(republiqueName, expectedDate, expectedTime, expectedTotalForMeatMenu)
        }
    }
}
