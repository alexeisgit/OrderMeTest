package com.github.tarcv.orderme.app.ui.tests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.login.LoginManager
import com.github.tarcv.orderme.app.ui.robots.facebookLogin
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.utils.DateUtill
import com.github.tarcv.orderme.app.ui.robots.facebookContinueLogin
import org.junit.After

open class BaseTest {
    @After
    fun tearDown() {
        LoginManager.getInstance().logOut()
        val sharedPreferences = InstrumentationRegistry.getInstrumentation().targetContext
                .getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    fun skipLogin() {
        login {
            loginLater()
            sleep()
        }
    }

    fun getExpectedReservationDate(daysToAdd: Int): String {
        val date = DateUtill.calculateDateFromToday(daysToAdd)
        return "${date.third}-${date.second}-${date.first}"
    }

    fun loginWithFacebook() {
        login {
            tapLoginButton()
            sleep(10000)
        }

        facebookLogin {
            login()
            sleep(7000)
        }

        facebookContinueLogin {
            tapOnContinueButton()
            sleep(10000)
        }
    }

    val oceanSeafoodName = "Ocean Seafood"
    val beautyEssexName = "Beauty & Essex"
    val hakkasanName = "Hakkasan"
    val republiqueName = "Republique"
    val romanovName = "Romanov"
    val burgerName = "Burger"
    val reservationTime = "21:30"
}
