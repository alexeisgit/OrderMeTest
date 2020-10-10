package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.ChildViewAction
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

fun menuDetails(chooseMenuFunction: MenuDetailsRobot.() -> Unit) =
        MenuDetailsRobot().apply(chooseMenuFunction)

class MenuDetailsRobot {

    private val menuRecyclerView: Matcher<View> = withId(R.id.menu_recycler)
    private val bucketValue = withId(R.id.bucket_textview)

    fun addToCart(itemName: String) {
        onView(menuRecyclerView)
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                (hasDescendant(withText(itemName)), ChildViewAction(R.id.plus_button, click())))
    }

    fun getShoppingCartValue(): Double {
        var price: AtomicReference<String> = AtomicReference()
        onView(bucketValue).perform(getText(price))
        return price.get().toDouble()
    }

    private fun getElementText(elementMatcher: Matcher<View>): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(elementMatcher).perform(getText(textReference))
        return textReference.toString()
    }

    fun verifyBucketValue() = getElementText(bucketValue)

    fun sleep() = Thread.sleep(2000)
}
