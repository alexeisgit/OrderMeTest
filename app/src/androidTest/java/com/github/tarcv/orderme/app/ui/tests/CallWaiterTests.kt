package com.github.tarcv.orderme.app.ui.tests
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.tarcv.orderme.app.ui.SplashActivity
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.robots.restaurant
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import com.github.tarcv.orderme.app.ui.robots.callAWaiterOptions
import com.github.tarcv.orderme.app.ui.robots.qrScreen
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

        qrScreen {
            enterRepubliqueQRCode()
            sleep()
            tapSubmitButton()
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