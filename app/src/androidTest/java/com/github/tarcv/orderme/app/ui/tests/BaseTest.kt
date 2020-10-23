package com.github.tarcv.orderme.app.ui.tests

import com.github.tarcv.orderme.app.ui.robots.facebookContinueLogin
import com.github.tarcv.orderme.app.ui.robots.facebookLogin
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.utils.DateUtill
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import org.junit.Rule
import java.text.SimpleDateFormat
import java.util.Calendar

open class BaseTest {

    @get: Rule
    var clearPreferencesRule = ClearPreferencesRule()

    @get: Rule
    var clearDatabaseRule = ClearDatabaseRule()

    @get: Rule
    var clearFilesRule = ClearFilesRule()

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
            sleep(10000)
        }

        facebookLogin {
            login()
            sleep(10000)
        }

        facebookContinueLogin {
            tapOnContinueButton()
            sleep(10000)
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
