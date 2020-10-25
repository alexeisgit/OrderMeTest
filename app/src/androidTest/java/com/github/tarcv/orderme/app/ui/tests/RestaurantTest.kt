package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.saladsAndVegetablesList
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

    @Test
    fun verifyRestTitle() {
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
    fun verifyHakkasanCallaWaiterButtonIsVisible() {
        skipLogin()

        restaurantList {
            selectRestaurant("Hakkasan")
        }

        restaurant {
            checkIfButtonIsVisible("Call a waiter")
        }
    }

    @Test
    fun verifyRomanovDetectTableBtn1() {
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
        skipLogin()

        restaurantList {
            selectRestaurant(romanovName)
        }

        restaurant {
            checkIfTextIsDisplayed("Menu")
        }
    }

    @Test
    fun verifyLocationButtonIsVisible() {
        skipLogin()

        restaurantList {
            selectRestaurant(oceanSeafoodName)
        }

        restaurant {
            checkIfLocationBtnIsDisplayed()
        }
    }

    @Test
    fun verifyHakkasanMenuButtonIsVisible() {
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
