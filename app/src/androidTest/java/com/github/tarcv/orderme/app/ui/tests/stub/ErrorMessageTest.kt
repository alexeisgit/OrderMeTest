package com.github.tarcv.orderme.app.ui.tests.stub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.tests.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ErrorMessageTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SplashActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)
            .around(mActivityTestRule)

    private val wrongQrErrMsgTxt: String = "QR Code is not compatible with OrderMe " +
            "(not a place code?)"
    private var correctQR: String = "3_5"
    private var wrongQR: String = "35"

    @Test
    fun verifyCorrectErrorIsDisplayed() {
        getPlaces("places.json", always = true)

        skipLogin()

        restaurantList {
            tapQRBtn()
        }

        qrScreen {
            enterQRCode(correctQR)
            tapSubmitButton()
        }

        restaurant {
            checkIfTitleIsDisplayed()
            checkIfTextIsDisplayed(republiqueName)
            tapOnDetectTable()
        }

        qrScreen {
            enterQRCode(wrongQR)
            tapSubmitButton()
        }

        popUpMessage {
            errorMessageIsDisplayed()
            assertEquals(wrongQrErrMsgTxt, getWrongQrErrorMessageText())
        }
    }
}
