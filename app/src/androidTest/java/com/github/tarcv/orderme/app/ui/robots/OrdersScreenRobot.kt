package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.github.tarcv.orderme.app.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

fun ordersList(ordersListFunction: OrdersListRobot.() -> Unit) =
    OrdersListRobot().apply(ordersListFunction)

class OrdersListRobot : BaseRobot() {
    private val orderRecyclerViewMatcher = withId(R.id.order_recyclerview)

    private val orderRestaurantMatcher = withId(R.id.order_restaurant)
    private val orderDateMatcher = withId(R.id.order_date)
    private val orderCheckMatcher = withId(R.id.order_check)
    private val orderTimeMatcher = withId(R.id.order_time)

    fun isOrderDisplayed(
        orderRestaurant: String,
        orderDate: String,
        orderTime: String,
        orderCheck: String
    ) {
        val orderMatcher: Matcher<View> = allOf(
                hasDescendant(allOf(orderRestaurantMatcher, withText(orderRestaurant))),
                hasDescendant(allOf(orderDateMatcher, withText(orderDate))),
                hasDescendant(allOf(orderCheckMatcher, withText(orderCheck))),
                hasDescendant(allOf(orderTimeMatcher, withText(orderTime)))
                )

        onView(orderRecyclerViewMatcher)
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                (orderMatcher, scrollTo()))
                .check(matches(isDisplayed()))
    }
}
