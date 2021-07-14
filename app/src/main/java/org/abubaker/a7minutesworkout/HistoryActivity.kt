package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@HistoryActivity, R.layout.activity_history)

        // Enable ActionBar using setSupportActionBar()
        setSupportActionBar(binding.toolbarHistoryActivity)

        // Assigning recently enabled ActionBar to the variable, using supportActionBar
        val actionbar = supportActionBar

        if (actionbar != null) {
            // Enable back Button
            actionbar.setDisplayHomeAsUpEnabled(true)

            // Changing the default title
            actionbar.title = "History"
        }

        binding.toolbarHistoryActivity.setNavigationOnClickListener {
            // Redirect to the HOME Screen
            onBackPressed()
        }

    }

}