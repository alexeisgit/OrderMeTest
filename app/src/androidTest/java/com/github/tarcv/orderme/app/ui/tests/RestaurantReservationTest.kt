package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.reservationsList
import com.github.tarcv.orderme.app.ui.robots.reservation
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantReservationTest : BaseTest() {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    private val republiqueQRCode = "3_5"
    private val phoneNumber = "04358889067"
    private val numberOfPeople = "3"
    private val numberOfDays = 5

    @Test
    fun verifyReservationFlowForFiveDays() {

        loginWithFacebook()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
            sleep()
        }

        restaurant {
            tapReservation()
        }

        reservation {
            sleep()
            enterPhoneNumber(phoneNumber)
            enternumberOfPeople(numberOfPeople)
            selectReservationDate(numberOfDays)
            selectReservationTime(reservationTime)
            tapOnBookButton()
        }

        popUpMessage {
            sleep()
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Your reservation was made",
                    getAlertMessageText())
            tapAlertOkButton()
        }

        reservation {
            tapReservationsTab()
            sleep()
        }

        reservationsList {
            tapOnFutureReservationsTab()
            scrollToLastReservationItem()
            checkReservationsDetail(republiqueName)
            checkReservationsDetail(getExpectedReservationDate(numberOfDays))
            checkReservationsDetail(reservationTime)
        }
    }
}
