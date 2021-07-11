package org.abubaker.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@FinishActivity, R.layout.activity_finish)
        // setContentView(R.layout.activity_finish)

        /**
         * Since I am using "Binding", that's why "Direct Assignment" is causing issue:
         * val actionbar = setSupportActionBar(binding.toolbarFinishActivity)
         *
         * Alternative approach is:
         */

        // Enable ActionBar using setSupportActionBar()
        setSupportActionBar(binding.toolbarFinishActivity)

        // Assigning recently enabled ActionBar to the variable, using supportActionBar
        val actionbar = supportActionBar

        if (actionbar != null) {
            // Set back Button
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarFinishActivity.setNavigationOnClickListener {
            // It will try to go back, but since there will be no pending exercise, that's why
            // it will redirect to the HOME Screen
            onBackPressed()
        }

        // Button: Finish
        binding.btnFinish.setOnClickListener {
            finish()
        }


    }
}