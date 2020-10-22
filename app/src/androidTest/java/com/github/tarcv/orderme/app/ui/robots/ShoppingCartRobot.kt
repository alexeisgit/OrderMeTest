package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R

fun shoppingCart(shoppingCartFunction: ShoppingCartRobot.() -> Unit) =
        ShoppingCartRobot().apply { shoppingCartFunction() }

private val acceptBtnMatcher = withText("Accept")
private const val addressInput = "123 Fake Street, City"
private val commentMatcher = withId(R.id.bucket_comment)

class ShoppingCartRobot : BaseRobot() {

    fun inputAddress() = enterText(commentMatcher, addressInput)

    fun selectAccept() = tapBy(acceptBtnMatcher)
}
