package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.menuDetails
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ShoppingCartTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val republiqueQRCode = "3_5"
    private val republiqueId = 3

    @Test
    fun verifyDefaultShoppingCartValue() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            getMenu(republiqueId, "menuRepublique.json", always = true)
            tapMenu()
        }

        menu {
            selectFishMenu()
        }

        menuDetails {
            assertEquals("0.0", verifyBucketValue())
        }
    }

    @Test
    fun verifyShoppingCartTotal() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            getMenu(republiqueId, "menuRepublique.json", always = true)
            tapMenu()
        }

        menu {
            selectSaladsMenu()
        }

        menuDetails {
            addToCart("COLEMAN FARMS LITTLE GEMS")
            addToCart("BLACK & WHITE SALAD")
            addToCart("OCTOPUS")
            assertEquals("77.0", getShoppingCartValue())
        }
    }
}
