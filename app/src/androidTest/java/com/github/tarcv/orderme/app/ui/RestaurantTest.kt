package com.github.tarcv.orderme.app.ui

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.Screens.LoginScreen
import com.github.tarcv.orderme.app.ui.Screens.RestaurantListScreen
import com.github.tarcv.orderme.app.ui.Screens.RestaurantScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun verifyRestTitle() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Ocean Seafood")

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTitleIsDisplayed()
    }

    @Test
    fun verifyHakkasanCallaWaiterButtonIsVisible() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Hakkasan")

        sleep(2000)

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfButtonIsVisible("Call a waiter")
    }

    @Test
    fun verifyRomanovDetectTableBtn1() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Romanov")

        sleep(2000)

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTextIsDisplayed("Detect table")
    }

    @Test
    fun verifyRomanovRestaurantLocation() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()
        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Romanov")
        sleep(2000)

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTextIsDisplayed("Location")
    }

    fun verifyBeautyEssexPhoneButton() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Beauty & Essex")

        sleep(2000)

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTextIsDisplayed("Phone")
    }

    @Test
    fun verifyRomanovMenuBtn() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Romanov")

        sleep(2000)

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTextIsDisplayed("Menu")
    }

    @Test
    fun verifyLocationButtonIsVisible() {
            val loginScreen = LoginScreen()
            loginScreen.loginLater()

            sleep(2000)

            val restaurantListScreen = RestaurantListScreen()
            restaurantListScreen.selectRestaurant("Ocean Seafood")

            sleep(2000)

            val restaurantScreen = RestaurantScreen()
            restaurantScreen.checkIfTextIsDisplayed("Location")
        }

    @Test
    fun verifyHakkasanMenuButtonIsVisible() {

        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Hakkasan")

        val restaurantScreen = RestaurantScreen()

        restaurantScreen.checkIfTextIsDisplayed("Menu")
    }

    @Test
    fun verifyHakkasanReservationBtn() {
        val loginScreen = LoginScreen()
        loginScreen.loginLater()

        sleep(2000)

        val restaurantListScreen = RestaurantListScreen()
        restaurantListScreen.selectRestaurant("Hakkasan")

        val restaurantScreen = RestaurantScreen()
        restaurantScreen.checkIfTextIsDisplayed("Reservation")
    }
}
