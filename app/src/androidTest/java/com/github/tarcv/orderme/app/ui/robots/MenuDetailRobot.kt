package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.tarcv.orderme.app.R
import com.github.tarcv.orderme.app.ui.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

fun menuDetail(menuDetailFunction: MenuDetailRobot.() -> Unit) =
        MenuDetailRobot().apply(menuDetailFunction)

class MenuDetailRobot {

    private val bucketValue = withId(R.id.bucket_textview)

    private fun getElementText(elementMatcher: Matcher<View>): String {
        val textReference: AtomicReference<String> = AtomicReference()
        onView(elementMatcher).perform(getText(textReference))
        return textReference.toString()
    }

    fun verifyBucketValue() = getElementText(bucketValue)

    fun sleep() = Thread.sleep(2000)
}