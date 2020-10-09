package com.github.tarcv.orderme.app.ui.tests
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.detectTable
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.callAWaiterOptions
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CallWaiterTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    private val validQRCode = "3_5"

    @Test
    fun verifyRepubliqueBringAMenuSuccess() {
        login {
            loginLater()
            sleep()
        }

        restaurantList {
            selectRestaurant("Republique")
        }

        restaurant {
            tapOnDetectTable()
        }

        detectTable {
            enterQrCode(validQRCode)
            tapOnSubmit()
        }

        restaurant {
            tapOnCallAWaiter()
        }

        callAWaiterOptions {
            tapOnBringAMenu()
            sleep()
            assertEquals("Alert title is incorrect", "Success!", getAlertTitleText())
            assertEquals("Alert message is incorrect", "Waiter is on his way",
                    getAlertMessageText())
        }
    }
}