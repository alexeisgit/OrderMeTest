package com.github.tarcv.orderme.app.ui.tests

import androidx.test.espresso.IdlingRegistry
import com.github.tarcv.orderme.app.Utils
import com.github.tarcv.orderme.app.App
import com.github.tarcv.orderme.app.ui.di.AndroidTestAppComponent
import com.github.tarcv.orderme.app.ui.robots.facebookContinueLogin
import com.github.tarcv.orderme.app.ui.robots.facebookLogin
import com.github.tarcv.orderme.app.ui.robots.login
import com.github.tarcv.orderme.app.ui.utils.DateUtill
import com.github.tarcv.orderme.app.ui.utils.readJSONFromAsset
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import org.junit.After
import io.fabric8.mockwebserver.DefaultMockServer
import org.junit.Before
import org.junit.Rule
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

open class BaseTest {

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(Utils.countingIdlingResource)
        (App.component as AndroidTestAppComponent).injectBaseTest(this)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(Utils.countingIdlingResource)
    }

    @Inject
    lateinit var mockWebServer: DefaultMockServer

    @get: Rule
    var clearPreferencesRule = ClearPreferencesRule()

    @get: Rule
    var clearDatabaseRule = ClearDatabaseRule()

    @get: Rule
    var clearFilesRule = ClearFilesRule()

    fun getMock(endPoint: String, statusCode: Int, filePath: String) {
        mockWebServer.expect()
                .get()
                .withPath(endPoint)
                .andReturn(statusCode, readJSONFromAsset(filePath))
                .once()
    }

    fun stubOk(endPoint: String) {
        mockWebServer.expect()
                .get()
                .withPath(endPoint)
                .andReturn(200, "")
                .always()
    }

    fun postMock(endPoint: String, statusCode: Int, filePath: String) {
        mockWebServer.expect()
                .post()
                .withPath(endPoint)
                .andReturn(statusCode, readJSONFromAsset(filePath))
                .once()
    }

    fun authMock() {
        stubOk("/places")
        stubOk("menu/orders")
        stubOk("/reserve")
    }

    fun mockGetPlaces(filePath: String) {
        getMock("/places", 200, filePath)
    }

    fun mockGetMenu(filePath: String) {
        getMock("/menu/3", 200, filePath)
    }

    fun mockGetOrders(filePath: String) {
        getMock("/menu/orders", 200, filePath)
    }

    fun mockPostOrder(filePath: String) {
        postMock("/menu/order", 200, filePath)
    }

    fun mockReserve() {
        postMock("/reserve", 200, "reserve.json")
    }

    fun getReserve() {
        getMock("/reserve", 200, "reserveFull.json")
    }

    fun skipLogin() {
        login {
            loginLater()
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
            sleep()
        }
    }

    open fun loginWithFacebookMock() {
        mockWebServer.expect()
                .get()
                .withPath("/places")
                .andReturn(200, readJSONFromAsset("places.json"))
                .once()

        mockWebServer.expect()
                .get()
                .withPath("/menu/orders")
                .andReturn(200, readJSONFromAsset("userorders.json"))
                .once()

        mockWebServer.expect()
                .get()
                .withPath("/reserve")
                .andReturn(200, readJSONFromAsset("reserve.json"))
                .once()

        App.sharedPreferences.edit().apply {
            putString(App.LOGIN_TOKEN, "facebookToken")
            putString(App.LOGIN_NAME, "John Smith")
            putInt(App.LOGIN_ID, 91)
            putString(App.LOGIN_USER_ID, "101282011769085")
        }.commit()

        skipLogin()
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
