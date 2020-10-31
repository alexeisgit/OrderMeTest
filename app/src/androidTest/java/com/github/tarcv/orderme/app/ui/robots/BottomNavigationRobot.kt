package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R

fun bottomNav(bottomNavFunction: BottomNavigationRobot.() -> Unit) =
    BottomNavigationRobot().apply(bottomNavFunction)

class BottomNavigationRobot : BaseRobot() {

    private val orderNavMatcher = withId(R.id.navigation_orders)
    private val reserveNavMatcher = withId(R.id.navigation_reservation)

    fun tapOrdersTab() = tapBy(orderNavMatcher)
}