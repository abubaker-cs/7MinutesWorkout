package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityHistoryBinding
import org.abubaker.a7minutesworkout.db.SqliteOpenHelper

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

        // Calling a function for getting the list of completed dates when the History screen is launched
        getAllCompletedDates()

    }

    /**
     * Function is used to get the list exercise completed dates.
     */
    private fun getAllCompletedDates() {

        // Instance of the SqliteOpenHelper.kt class
        val dbHandler = SqliteOpenHelper(this, null)

        // Get list of data from the history table
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList()

        // List items are printed in log.
        for (i in allCompletedDatesList) {
            Log.e("Date : ", "" + i)
        }

    }

}