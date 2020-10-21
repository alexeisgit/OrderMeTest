package com.github.tarcv.orderme.app.ui.tests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.login.LoginManager
import com.github.tarcv.orderme.app.ui.robots.facebookLogin
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.utils.DateUtill
import com.github.tarcv.orderme.app.ui.robots.facebookContinueLogin
import org.junit.After
import java.text.SimpleDateFormat
import java.util.Calendar

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
        var month = if (date.second >= 10) "${date.second}" else "0${date.second}"
        var day = if (date.first >= 10) "${date.first}" else "0${date.first}"
        var year = date.third
        return "$year-$month-$day"
    }

    fun loginWithFacebook() {
        login {
            tapLoginButton()
            sleep(5000)
        }

        facebookLogin {
            login()
            sleep(5000)
        }

        facebookContinueLogin {
            tapOnContinueButton()
            sleep(5000)
        }
    }

    fun getCurrentDate() = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)

    fun getCurrentTime() = SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)

    val oceanSeafoodName = "Ocean Seafood"
    val beautyEssexName = "Beauty & Essex"
    val hakkasanName = "Hakkasan"
    val republiqueName = "Republique"
    val romanovName = "Romanov"
    val burgerName = "Burger"
}
