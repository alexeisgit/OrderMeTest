package com.github.tarcv.orderme.app.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling

import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matchers.allOf
import java.util.concurrent.atomic.AtomicReference

fun saladsAndVegetablesList
        (saladsAndVegetablesListFunction: SaladsAndVegetablesListRobot.() -> Unit) =
        SaladsAndVegetablesListRobot().apply(saladsAndVegetablesListFunction)

class SaladsAndVegetablesListRobot {

    private val defaultValueOfMenuItem = withId(R.id.menu_count)

    fun checkDefaultValueOfEachMenuItem(text: String): String {
        val textReference: AtomicReference<String> = AtomicReference()

        onView(allOf(defaultValueOfMenuItem, withParent(hasSibling(withText(text)))))
                .perform(getText(textReference))
        val actualText = textReference.toString()
        return actualText
    }
}
