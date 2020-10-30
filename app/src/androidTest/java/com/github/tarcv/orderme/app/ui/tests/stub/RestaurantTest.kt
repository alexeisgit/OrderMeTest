package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.saladsAndVegetablesList
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class RestaurantTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val menuOption = "Menu"

    private val defaultValueOfMenuItemExpected = "0"

    private val colemanFarms = "COLEMAN FARMS LITTLE GEMS"

    private val blackWhiteSalad = "BLACK & WHITE SALAD"

    private val octopus = "OCTOPUS"

    private val republiqueQRCode = "3_5"

    private val republiqueId: Int = 3

    @Test
    fun verifyRestTitle() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(oceanSeafoodName)
        }

        restaurant {
            checkIfTitleIsDisplayed()
            assertEquals(oceanSeafoodName, getRestaurantTitleText())
        }
    }

    @Test
    fun verifyRomanovDetectTableBtn1() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(romanovName)
        }

        restaurant {
            checkIfTextIsDisplayed("Detect table")
        }
    }

    @Test
    fun verifyRomanovRestaurantLocation() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant("Romanov")
        }

        restaurant {
            checkIfTextIsDisplayed("Romanov")
        }
    }

    @Test
    fun verifyBeautyEssexPhoneButton() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(beautyEssexName)
        }

        restaurant {
            checkIfButtonIsVisible("Phone")
        }
    }

    @Test
    fun verifyRomanovMenuBtn() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(romanovName)
        }

        restaurant {
            checkIfTextIsDisplayed("Menu")
        }
    }

    @Test
    fun verifyHakkasanMenuButtonIsVisible() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant("Hakkasan")
        }

        restaurant {
            checkIfTextIsDisplayed("Menu")
        }
    }

    @Test
    fun verifyHakkasanReservationBtn() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant(hakkasanName)
        }

        restaurant {
            checkIfTextIsDisplayed("Reservation")
        }
    }

    @Test
    fun verifyBurgerRest() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant("Burger")
        }

        restaurant {
            checkIfTitleIsDisplayed()
        }
    }

    @Test
    fun verifyRestImageIsVisible() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            selectRestaurant("Ocean Seafood")
        }
        restaurant {
            checkIfImageIsDisplayed()
        }
    }

    @Test
    fun verifyQRCodeNavigatedToMenuScreen() {
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
            assertEquals("Menu", menuOption)
        }
    }

    @Test
    fun verifyDefaultNumberOfEachMenuItem() {
        getPlaces("places.json", always = true)
        getMenu(republiqueId, "menuRepublique.json", always = true)

        skipLogin()

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

        saladsAndVegetablesList {
            assertEquals(defaultValueOfMenuItemExpected,
                checkDefaultValueOfEachMenuItem(colemanFarms))
            assertEquals(defaultValueOfMenuItemExpected,
                checkDefaultValueOfEachMenuItem(blackWhiteSalad))
            assertEquals(defaultValueOfMenuItemExpected,
                checkDefaultValueOfEachMenuItem(octopus))
        }
    }
}
