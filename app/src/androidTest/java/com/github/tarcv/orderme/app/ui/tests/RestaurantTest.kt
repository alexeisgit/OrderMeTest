package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.menu
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.saladsAndVegetablesList
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    private val menuOption = "Menu"

    private val defaultValueOfMenuItemExpected = "0"

    private val colemanFarms = "COLEMAN FARMS LITTLE GEMS"

    private val blackWhiteSalad = "BLACK & WHITE SALAD"

    private val octopus = "OCTOPUS"

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
        login {
            loginLater()
            sleep()
        }

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
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Romanov")
        }

        restaurant {
            checkIfTextIsDisplayed("Romanov")
        }
    }

    @Test
    fun verifyBeautyEssexPhoneButton() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Beauty & Essex")
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
        login {
            loginLater()
            sleep()
        }

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
        login {
            loginLater()
            sleep()
        }
        restaurantList {
            selectRestaurant("Burger")
        }

        restaurant {
            checkIfTitleIsDisplayed()
        }
    }

    @Test
    fun verifyRestImageIsVisible() {
        login {
            loginLater()
            sleep()
        }
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
            enterRepubliqueQRCode()
            sleep()
            tapSubmitButton()
        }

        restaurant {
            assertEquals("Menu", menuOption)
        }
    }

    @Test
    fun verifyDefaultNumberOfEachMenuItem() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterRepubliqueQRCode()
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
