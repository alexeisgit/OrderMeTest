package com.github.tarcv.orderme.app.ui.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.Matcher

class ChildViewAction(val id: Int, val action: ViewAction) : ViewAction {
    override fun getDescription(): String = "Do action on child view"

    override fun getConstraints(): Matcher<View> = isA(View::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        val child: View? = view?.findViewById(id)
        action.perform(uiController, child)
    }
}