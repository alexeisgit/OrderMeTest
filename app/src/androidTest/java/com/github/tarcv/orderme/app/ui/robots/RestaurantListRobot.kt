package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.RecyclerViewMatcher.Companion.recyclerElementCount
import org.hamcrest.Matcher

fun restaurantList(listFunction: RestaurantListRobot.() -> Unit) =
        RestaurantListRobot().apply(listFunction)

class RestaurantListRobot {

    private val restaurantRecyclerMatcher: Matcher<View> = withId(R.id.restaurantRecycler)

    fun selectRestaurant(name: String) {
        onView(withId(R.id.restaurantRecycler))
                .perform(actionOnItem<RecyclerView.ViewHolder>
                (hasDescendant(withText(name)), scrollTo()))

        onView(withText(name))
                .perform(click())
    }

    fun checkNumberOfRestaurants(count: Int) = onView(restaurantRecyclerMatcher)
                .check(matches(recyclerElementCount(count)))
}
