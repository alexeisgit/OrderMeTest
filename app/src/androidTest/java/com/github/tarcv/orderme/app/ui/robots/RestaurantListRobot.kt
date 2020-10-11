package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.RecyclerViewMatcher.Companion.recyclerElementCount
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

fun restaurantList(listFunction: RestaurantListRobot.() -> Unit) =
        RestaurantListRobot().apply(listFunction)

class RestaurantListRobot : BaseRobot() {

    private val searchField: Matcher<View> = withId(R.id.searchView)
    private val restaurantRecyclerMatcher: Matcher<View> = withId(R.id.restaurantRecycler)
    private val restTitleMatcher: Matcher<View> = withId(R.id.titleText)
    private val qrBtn = withId(R.id.searchBtn)

    fun selectRestaurant(name: String) {
        onView(withId(R.id.restaurantRecycler))
                .perform(actionOnItem<RecyclerView.ViewHolder>
                (hasDescendant(withText(name)), scrollTo()))

        tapBy(withText(name))
    }

    fun searchRestaurantName(name: String) {
        onView(searchField).perform(typeText(name))
    }

    fun checkNumberOfRestaurants(count: Int) = onView(restaurantRecyclerMatcher)
                .check(matches(recyclerElementCount(count)))

    fun getRestaurantTitleText(): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(restTitleMatcher).perform(getText(textReference))
        val actualText = textReference.toString()
        return actualText
    }

    fun tapQRBtn() = tapBy(qrBtn)
}
