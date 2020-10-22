package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

fun reservationsList(ReservationsListFunction: ReservationsListRobot.() -> Unit) =
        ReservationsListRobot().apply { ReservationsListFunction() }

class ReservationsListRobot : BaseRobot() {

    private val futureReservationsTabMatcher = withText("Future reservations")
    private val reservationsRecyclerMatcher = withId(R.id.reservation_recycler)
    private val reservationRestaurantMatcher = withId(R.id.reservation_restaurant)
    private val reservationDateMatcher = withId(R.id.reservation_date)
    private val reservationTimeMatcher = withId(R.id.reservation_time)

    fun isReservationDetailsDisplayed(
        reservationRestaurant: String,
        reservationDate: String,
        reservationTime: String
    ) {
        val reservationMatcher: Matcher<View> = allOf(
                hasDescendant(allOf(reservationRestaurantMatcher, withText(reservationRestaurant))),
                hasDescendant(allOf(reservationDateMatcher, withText(reservationDate))),
                hasDescendant(allOf(reservationTimeMatcher, withText(reservationTime)))
        )

        onView(reservationsRecyclerMatcher)
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                (reservationMatcher, ViewActions.scrollTo()))
                .check(matches(ViewMatchers.isDisplayed()))
    }

    fun tapOnFutureReservationsTab() = tapBy(futureReservationsTabMatcher)
}
