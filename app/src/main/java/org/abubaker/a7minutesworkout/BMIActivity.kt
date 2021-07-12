package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BMIActivity, R.layout.activity_bmi)

        // Enable ActionBar using setSupportActionBar()
        setSupportActionBar(binding.toolbarBmiActivity)

        // Assigning recently enabled ActionBar to the variable, using supportActionBar
        val actionbar = supportActionBar

        if (actionbar != null) {
            // Set back Button
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE ME !!!"
        }

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            // It will try to go back, but since there will be no pending exercise, that's why
            // it will redirect to the HOME Screen
            onBackPressed()
        }

    }


}