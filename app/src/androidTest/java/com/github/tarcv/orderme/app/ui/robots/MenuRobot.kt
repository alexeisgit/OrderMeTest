package com.github.tarcv.orderme.app.ui.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

fun menu(menuFunction: MenuRobot.() -> Unit) =
        MenuRobot().apply(menuFunction)

class MenuRobot : BaseRobot() {

    private val saladsAndVeggiesMenu: Matcher<View> = withText("SALADS AND VEGETABLES")
    private val fishMenu: Matcher<View> = withText("FISH")
    private val pastaMenu: Matcher<View> = withText("PASTA")
    private val meatMenu: Matcher<View> = withText("MEAT")

    fun selectSaladsMenu() = tapBy(saladsAndVeggiesMenu)
    fun selectFishMenu() = tapBy(fishMenu)
    fun selectPastaMenu() = tapBy(pastaMenu)
    fun selectMeatMenu() = tapBy(meatMenu)
}
