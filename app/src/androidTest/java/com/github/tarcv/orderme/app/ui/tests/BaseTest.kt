package com.github.tarcv.orderme.app.ui.tests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tarcv.orderme.app.ui.robots.facebookContinueLogin
import com.github.tarcv.orderme.app.ui.robots.facebookLogin
import com.github.tarcv.orderme.app.ui.robots.login
import org.junit.After

open class BaseTest {

    @After
    fun tearDown() {
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

    fun loginWithFacebook() {
        facebookLogin {
            login()
            sleep(6000)
        }

        facebookContinueLogin {
            tapOnContinueButton()
            sleep(6000)
        }
    }

    val oceanSeafoodName = "Ocean Seafood"
    val beautyEssexName = "Beauty & Essex"
    val hakkasanName = "Hakkasan"
    val republiqueName = "Republique"
    val romanovName = "Romanov"
    val burgerName = "Burger"
}
