package com.rba.counter.view.sample.base

import android.app.Activity
import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.rba.counter.view.sample.util.MatcherUtil
import org.hamcrest.Matchers
import org.junit.Before

open class BaseUiTestActivity(private val clazz: Class<out Activity>) {

    val context: Application by lazy { ApplicationProvider.getApplicationContext<Application>() }

    @Before
    open fun setup() {
        ActivityScenario.launch(clazz)
    }

    protected fun testTextSize(id: Int, expectedTextSize: Int) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(id), ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(MatcherUtil.withTextSize(expectedTextSize)))
    }

    protected fun testTextColor(id: Int, expectedTextColorId: Int) {
        Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(MatcherUtil.withTextColor(expectedTextColorId)))
    }

    protected fun testCornerRadius(id: Int, expectedCornerRadius: Int) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(id), ViewMatchers.isDisplayed())).check(
            ViewAssertions.matches(MatcherUtil.withCornerRadius(expectedCornerRadius))
        )
    }
}