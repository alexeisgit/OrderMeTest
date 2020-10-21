package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.DateUtill
import org.hamcrest.Matcher

fun reservation(reservationFunction: ReservationRobot.() -> Unit) =
        ReservationRobot().apply { reservationFunction() }

class ReservationRobot : BaseRobot() {

    private val numberOfPeopleField: Matcher<View> = withId(R.id.make_reservation_people)
    private val phoneNumberMatcher = withId(R.id.make_reservation_phone)
    private val numberOfPeopleMatcher = withId(R.id.make_reservation_people)
    private val bookButtonMatcher = withText("Book")

    fun enterNumberOfPeople(numberOfPeople: String) = enterText(numberOfPeopleField, numberOfPeople)

    fun selectReservationDate(daysToAdd: Int) {
        val date = DateUtill.calculateDateFromToday(daysToAdd)
        selectDate(date.first, date.second, date.third)
    }

    fun selectReservationTime(reservationTime: String) {
        val hour = reservationTime.split(":")[0].toInt()
        val min = reservationTime.split(":")[1].toInt()
        selectTime(hour, min)
    }

    fun enterPhoneNumber(phoneNumber: String) = enterText(phoneNumberMatcher, phoneNumber)

    fun enternumberOfPeople(numberOfPeople: String) =
            enterText(numberOfPeopleMatcher, numberOfPeople)

    fun tapOnBookButton() = tapBy(bookButtonMatcher)
}
