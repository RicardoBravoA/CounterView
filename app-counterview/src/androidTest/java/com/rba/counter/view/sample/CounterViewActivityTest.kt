package com.rba.counter.view.sample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rba.counter.view.sample.base.BaseUiTestActivity
import com.rba.counter.view.sample.util.MatcherUtil.withBackgroundColor
import com.rba.counter.view.sample.util.MatcherUtil.withCornerRadius
import com.rba.counter.view.sample.util.MatcherUtil.withTextColor
import com.rba.counter.view.sample.util.MatcherUtil.withTextSize
import org.junit.Test

class CounterViewActivityTest : BaseUiTestActivity(MainActivity::class.java) {

    private val textSize by lazy { context.resources.getDimensionPixelSize(R.dimen.dp_14) }

    private val cornerRadius by lazy { context.resources.getDimensionPixelSize(R.dimen.dp_8) }

    @Test
    fun testBackgroundCounterView() {
        val id = R.id.counterView
        onView(withId(id)).perform(click())

        onView(withId(R.id.counterView)).check(matches(isDisplayed()))
        onView(withId(id)).check(matches(isEnabled()))
        onView(withId(id)).check(matches(withBackgroundColor(android.R.color.transparent)))
        onView(withId(id)).check(matches(withCornerRadius(cornerRadius)))
        onView(withId(R.id.valueTextView)).check(matches(withTextColor(com.rba.counter.view.R.color.text_counter_view)))
        onView(withId(R.id.valueTextView)).check(matches(withTextSize(textSize)))
    }
}