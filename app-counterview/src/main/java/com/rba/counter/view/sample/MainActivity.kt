package com.rba.counter.view.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rba.counter.view.listener.CounterViewLimitListener
import com.rba.counter.view.listener.CounterViewUpDownListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), CounterViewLimitListener, CounterViewUpDownListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        counterView.onCounterViewLimitListener(this)
        counterView.onCounterViewUpDownListener(this)

        counterView.value
    }

    override fun onUpValue(value: Int) {
        Log.i("z- onUpValue", value.toString())
    }

    override fun onDownValue(value: Int) {
        Log.i("z- onDownValue", value.toString())
    }

    override fun onCounterViewDownLimit() {
        Toast.makeText(this, "Min limit", Toast.LENGTH_SHORT).show()
    }

    override fun onCounterViewUpLimit() {
        Toast.makeText(this, "Max limit", Toast.LENGTH_SHORT).show()
    }
}
