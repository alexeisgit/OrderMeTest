package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.matcher.ViewMatchers
import com.github.tarcv.orderme.app.R

fun bottomNav(bottomNavFunction: BottomNavigationRobot.() -> Unit) =
    BottomNavigationRobot().apply(bottomNavFunction)

class BottomNavigationRobot : BaseRobot() {

    private val orderNavMatcher = ViewMatchers.withId(R.id.navigation_orders)

    fun tapOrdersTab() = tapBy(orderNavMatcher)
}