package com.github.tarcv.orderme.app.ui.robots

fun facebookLogin(facebookLoginFunction: FacebookLoginRobot.() -> Unit) = FacebookLoginRobot()
        .apply { facebookLoginFunction() }

class FacebookLoginRobot : BaseRobot() {

    private val email = "oahuteam@yahoo.com"
    private val password = "temptemp123"
    private val passwordFieldId = "m_login_password"
    private val emailFieldId = "m_login_email"
    private val loginButtonName = "login"

    fun login() {
        typeInWebElementById(emailFieldId, email)
        typeInWebElementById(passwordFieldId, password)
        tapOnWebElementByName(loginButtonName)
    }
}