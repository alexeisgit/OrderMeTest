package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.RecyclerViewItemMatcher.Companion.hasItemAtPosition
import org.hamcrest.CoreMatchers.allOf

fun reservationsList(ReservationsListFunction: ReservationsListRobot.() -> Unit) =
        ReservationsListRobot().apply { ReservationsListFunction() }

class ReservationsListRobot : BaseRobot() {

    private val futureReservationsTabMatcher = withText("Future reservations")
    private val reservationsRecyclerMatcher = withId(R.id.reservation_recycler)

    fun tapOnFutureReservationsTab() = tapBy(futureReservationsTabMatcher)

    fun scrollToLastReservationItem() = scrollToLastItemOfRecyclerView(reservationsRecyclerMatcher)

    fun checkReservationsDetail(text: String) {
        val position = getRecyclerViewItemsCount(reservationsRecyclerMatcher) - 1
        onView(reservationsRecyclerMatcher)
                .check(matches(hasItemAtPosition(position, hasDescendant(allOf(withText(text))))))
    }
}
