package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R
import org.hamcrest.Matcher

fun shoppingCart(shoppingCartFunction: ShoppingCartRobot.() -> Unit) =
    ShoppingCartRobot().apply { shoppingCartFunction() }

private val acceptBtnMatcher: Matcher<View> = withText("Accept")
private const val addressInput = "123 Fake Street, City"
private val commentMatcher: Matcher<View> = ViewMatchers.withId(R.id.bucket_comment)

class ShoppingCartRobot : BaseRobot() {

    fun inputAddress() = enterText(commentMatcher, addressInput)

    fun selectAccept() = tapBy(acceptBtnMatcher)
}