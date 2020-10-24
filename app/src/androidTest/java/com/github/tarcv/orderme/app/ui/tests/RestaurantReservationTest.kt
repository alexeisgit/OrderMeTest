package com.github.tarcv.orderme.app.ui.tests

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.tarcv.orderme.app.Utils
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.reservationsList
import com.github.tarcv.orderme.app.ui.robots.reservation
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
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
    private val numberOfDays = 5
    private val numberOfDaysFlow14DaysTest = 14
    var expectedReservationTime = getCurrentTime()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(Utils.countingIdlingResource)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(Utils.countingIdlingResource)
    }

    @Test
    fun verifyReservationFlowForFiveDays() {
        login {
            loginWithFacebook()
            sleep()
        }

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
            selectReservationDate(numberOfDays)
            expectedReservationTime = getCurrentTime()
            selectReservationTime(expectedReservationTime)
            tapOnBookButton()
        }

        popUpMessage {
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Your reservation was made",
                    getAlertMessageText())
            tapAlertOkButton()
        }

        reservation {
            tapReservationsTab()
        }

        reservationsList {
            tapOnFutureReservationsTab()
            isReservationDetailsDisplayed(republiqueName,
                    getExpectedReservationDate(numberOfDays), expectedReservationTime)
        }
    }

    @Test
    fun verifyReservationFlowFor14Days() {
        loginWithFacebook()

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
            selectReservationDate(numberOfDaysFlow14DaysTest)
            selectReservationTime(expectedReservationTime)
            tapOnBookButton()
        }

        popUpMessage {
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Your reservation was made",
                    getAlertMessageText())
            tapAlertOkButton()
        }

        reservation {
            tapReservationsTab()
        }

        reservationsList {
            tapOnFutureReservationsTab()
            isReservationDetailsDisplayed(republiqueName,
                    getExpectedReservationDate(numberOfDaysFlow14DaysTest), expectedReservationTime)
        }
    }
}
