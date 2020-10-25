package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.reservation
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.reservationsList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)

class RestaurantReservation20DaysTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    private var correctQR: String = "3_5"
    private val theNumberOfDays = 20
    var expectedReservationTime = getCurrentTime()

    @Test
    fun restaurantReservationFlow20Days() {

        loginWithFacebook()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(correctQR)
            tapSubmitButton()
        }

        restaurant {
            tapReservation()
        }

        reservation {
            enterPhoneNumber("3333-333-3333")
            enterNumberOfPeople("3")
            selectReservationDate(theNumberOfDays)
            selectReservationTime(expectedReservationTime)
            tapOnBookButton()
        }

        popUpMessage {
            successMessageIsDisplayed()
            tapAlertOkButton()
            tapReservationsTab()
        }

        reservationsList {
            tapOnFutureReservationsTab()
            isReservationDetailsDisplayed(republiqueName,
                    getExpectedReservationDate(theNumberOfDays), expectedReservationTime)
        }
    }
}
