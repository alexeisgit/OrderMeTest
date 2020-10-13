package com.github.tarcv.orderme.app.ui.tests

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.popUpMessage
import com.github.tarcv.orderme.app.ui.robots.qrScreen
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ErrorMessageTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    private val wrongQrErrMsgTxt: String = "QR Code is not compatible with OrderMe " +
            "(not a place code?)"
    private var correctQR: String = "3_5"
    private var wrongQR: String = "35"

    @Test
    fun verifyCorrectErrorIsDisplayed() {
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
