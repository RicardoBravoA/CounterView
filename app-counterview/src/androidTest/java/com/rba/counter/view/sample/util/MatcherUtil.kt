package com.rba.counter.view.sample.util

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object MatcherUtil {

    fun withTextSize(expectedTextSize: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("with text size: ")
                description.appendValue(expectedTextSize)
            }

            override fun matchesSafely(textView: TextView?): Boolean {
                return textView!!.textSize.compareTo(expectedTextSize) == 0
            }
        }
    }

    fun withTextColor(expectedTextColorId: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("with text color: ")
                description.appendValue(expectedTextColorId)
            }

            override fun matchesSafely(textView: TextView?): Boolean {
                val colorId = ContextCompat.getColor(textView!!.context, expectedTextColorId)
                return textView.currentTextColor == colorId
            }
        }
    }

    fun withCornerRadius(expectedCornerRadius: Int): Matcher<Any> {
        return object : BoundedMatcher<Any, View>(View::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("with corner radius: ")
                description.appendValue(expectedCornerRadius)
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view?.background == null) {
                    return false
                }

                val gradientDrawable = view.background as GradientDrawable
                return gradientDrawable.cornerRadius.compareTo(expectedCornerRadius) == 0
            }
        }
    }

    fun withBackgroundColor(expectedColor: Int): Matcher<Any> {
        return object : BoundedMatcher<Any, View>(View::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("with color: ")
                description.appendValue(expectedColor)
            }

            override fun matchesSafely(view: View?): Boolean {
                val colorDrawable = view?.background as GradientDrawable

                return ContextCompat.getColorStateList(
                    view.context,
                    expectedColor
                )!!.defaultColor == colorDrawable.color!!.defaultColor
            }
        }
    }
}