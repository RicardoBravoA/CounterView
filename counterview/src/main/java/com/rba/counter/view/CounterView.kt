package com.rba.counter.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.rba.counter.view.listener.CounterViewLimitListener
import com.rba.counter.view.listener.CounterViewUpDownListener
import kotlinx.android.synthetic.main.counter_view_layout.view.*

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    /* Min value */
    var minValue: Int = MIN_VALUE
    /* Max value */
    var maxValue: Int = MAX_VALUE
    /* Up/Down value */
    var upDownValue: Int = DEFAULT_UP_DOWN_VALUE
    /* Disable mmax/min value */
    var disableMaxMinValue: Boolean = DISABLE_MAX_MIN_VALUE
    /* value */
    var value: Int = DEFAULT_VALUE
        set(newValue) {
            var currentValue = newValue
            if (currentValue < minValue || currentValue > maxValue) {
                currentValue = value
            }

            field = currentValue
            valueTextView.text = field.toString()
        }

    private var counterViewUpDownListener: CounterViewUpDownListener? = null
    private var counterViewLimitListener: CounterViewLimitListener? = null

    init {
        inflate(context, R.layout.counter_view_layout, this)
        background = ContextCompat.getDrawable(context, R.drawable.shape_counter_view)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CounterView, 0, 0
        )

        typedArray.let {

            minValue = typedArray.getInt(R.styleable.CounterView_counter_view_min, MIN_VALUE)

            maxValue = typedArray.getInt(R.styleable.CounterView_counter_view_max, MAX_VALUE)

            value = typedArray.getInt(R.styleable.CounterView_counter_view_value, DEFAULT_VALUE)

            upDownValue = typedArray.getInt(
                R.styleable.CounterView_counter_view_up_down_value,
                DEFAULT_UP_DOWN_VALUE
            )

            if (value < minValue || value > maxValue) {
                throw RuntimeException("Value is not in the indicated range")
            }

            typedArray.recycle()
        }

        upImageView.setOnClickListener {
            upValue()
        }

        downImageView.setOnClickListener {
            downValue()
        }
    }

    fun onCounterViewUpDownListener(counterViewUpDownListener: CounterViewUpDownListener) {
        this.counterViewUpDownListener = counterViewUpDownListener
    }

    fun onCounterViewLimitListener(counterViewLimitListener: CounterViewLimitListener) {
        this.counterViewLimitListener = counterViewLimitListener
    }

    private fun upValue() {
        val newValue = value
        if (newValue < maxValue) {
            value = newValue + upDownValue
            counterViewUpDownListener?.onUpValue(newValue + upDownValue)
            upImageView.isEnabled = true
            downImageView.isEnabled = true
        } else {
            if (disableMaxMinValue) upImageView.isEnabled = false
            downImageView.isEnabled = true
            counterViewLimitListener?.onCounterViewUpLimit()
        }
    }

    private fun downValue() {
        val newValue = value
        if (newValue > minValue) {
            value = newValue - upDownValue
            counterViewUpDownListener?.onDownValue(newValue - upDownValue)
            downImageView.isEnabled = true
            upImageView.isEnabled = true
        } else {
            if (disableMaxMinValue) downImageView.isEnabled = false
            upImageView.isEnabled = true
            counterViewLimitListener?.onCounterViewDownLimit()
        }
    }

    companion object {
        const val MIN_VALUE = -20
        const val MAX_VALUE = 20
        const val DEFAULT_VALUE = 1
        const val DEFAULT_UP_DOWN_VALUE = 1
        const val DISABLE_MAX_MIN_VALUE = true
    }
}