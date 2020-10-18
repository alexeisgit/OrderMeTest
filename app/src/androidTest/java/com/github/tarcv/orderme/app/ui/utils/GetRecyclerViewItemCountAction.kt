package com.github.tarcv.orderme.app.ui.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

fun getItemCount(itemsCount: AtomicReference<Int>): ViewAction =
        GetRecyclerViewItemCountAction(itemsCount)

class GetRecyclerViewItemCountAction(var itemsCount: AtomicReference<Int>) : ViewAction {
    override fun getDescription(): String = "Get number of items in Recycler view"

    override fun getConstraints(): Matcher<View> = CoreMatchers.isA(View::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        val actualCount = (view as RecyclerView).adapter?.itemCount
        itemsCount.set(actualCount)
    }
}