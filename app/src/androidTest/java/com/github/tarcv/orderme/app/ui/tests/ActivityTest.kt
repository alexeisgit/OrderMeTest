package com.github.tarcv.orderme.app.ui.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.tarcv.orderme.app.ui.TabBarActivity
import com.github.tarcv.orderme.app.ui.robots.restaurantList
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ActivityTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(TabBarActivity::class.java)

    @get: Rule
    var chain = RuleChain.outerRule(clearPreferencesRule)
            .around(activityRule)
            .around(clearDatabaseRule)
            .around(clearFilesRule)

    // TODO: Remove @Ignore tag when mocks are implemented
    @Ignore
    @Test
    fun verifyTapBarActivity() {
        restaurantList {
            checkNumberOfRestaurants(6)
        }
    }
}
