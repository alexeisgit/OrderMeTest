package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.screens.LoginScreen
import com.github.tarcv.orderme.app.ui.screens.RestaurantListScreen
import com.github.tarcv.orderme.app.ui.screens.RestaurantScreen
import com.github.tarcv.orderme.app.ui.SplashActivity
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
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Ocean Seafood")
        }

        restaurant {
            checkIfTitleIsDisplayed()
        }
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
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Romanov")
        }

        restaurant {
            checkIfTextIsDisplayed("Detect table")
        }
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
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Romanov")
        }

        restaurant {
            checkIfTextIsDisplayed("Menu")
        }
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
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Hakkasan")
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
}
