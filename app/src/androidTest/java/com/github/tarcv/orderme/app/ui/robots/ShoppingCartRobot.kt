package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun shoppingCart(shoppingCartFunction: ShoppingCartRobot.() -> Unit) =
        ShoppingCartRobot().apply { shoppingCartFunction() }

private val acceptBtnMatcher = withId(R.id.bucket_accept)
private val commentMatcher = withId(R.id.bucket_comment)

class ShoppingCartRobot : BaseRobot() {

    fun typeComment(comment: String) = enterText(commentMatcher, comment)

    fun selectAccept() = tapBy(acceptBtnMatcher)
}
