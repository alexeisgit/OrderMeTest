package com.github.tarcv.orderme.app.ui.tests.stub

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
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RestaurantReservationTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val republiqueQRCode = "3_5"
    private val phoneNumber = "04358889067"
    private val numberOfPeople = "3"
    private val expectedDate = "2120-11-27"
    private val expectedReservationTime = "21:30"

    @Test
    fun verifyReservationFlowForFutureReservation() {
        loginWithFacebookMock()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(republiqueQRCode)
            tapSubmitButton()
        }

        restaurant {
            tapReservation()
        }

        reservation {
            enterPhoneNumber(phoneNumber)
            enternumberOfPeople(numberOfPeople)
            selectStubDay()
            selectReservationTime(expectedReservationTime)
            mockReserve()
            getReserve()
            tapOnBookButton()
        }

        popUpMessage {
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Your reservation was made",
                    getAlertMessageText())
            tapAlertOkButton()
        }

        reservation {
            mockReserve()
            tapReservationsTab()
        }

        reservationsList {
            tapOnFutureReservationsTab()
            isReservationDetailsDisplayed(republiqueName,
                    expectedDate, expectedReservationTime)
        }
    }
}
