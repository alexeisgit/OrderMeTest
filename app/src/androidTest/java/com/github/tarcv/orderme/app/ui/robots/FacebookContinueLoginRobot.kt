package com.github.tarcv.orderme.app.ui.robots

fun facebookContinueLogin
        (loginFunction: FacebookContinueLoginRobot.() -> Unit) = FacebookContinueLoginRobot()
        .apply { loginFunction() }

class FacebookContinueLoginRobot : BaseRobot() {

    private val continueButtonName = "__CONFIRM__"

    fun tapOnContinueButton() {
        tapOnWebElementByName(continueButtonName)
    }
}
