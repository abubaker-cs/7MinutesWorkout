package org.abubaker.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityFinishBinding
import org.abubaker.a7minutesworkout.db.SqliteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

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

        // We are calling our function to store current formatted date + time into the database
        addDateToDatabase()

    }

    /**
     * Function is used to insert the current system date in the sqlite database.
     */
    private fun addDateToDatabase() {

        ///////////////////////////////////////
        // Step 1 - Getting current Date & Time
        ///////////////////////////////////////

        // Calendars Current Instance
        val currentInstance = Calendar.getInstance()

        // Current Date and Time of the system.
        val dateTime = currentInstance.time

        // Printed in the log.
        Log.e("Date : ", "" + dateTime)

        //////////////////////////////////////////////////////////
        // Step 2 - Re-formatting dateTime using a custom TEMPLATE
        //////////////////////////////////////////////////////////

        // We are creating a Template for DATE Format
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())

        // Using the sdf TEMPLATE we are re-formatting our previously stored dateTime variable
        val date = sdf.format(dateTime)

        // Formatted date is printed in the log.
        Log.e("Formatted Date : ", "" + date)

        ///////////////////////////////////
        // Step 3 - Storing in the DATABASE
        ///////////////////////////////////

        // Instance of the Sqlite Open Helper class.
        val dbHandler = SqliteOpenHelper(this, null)

        // Add date function is called.
        dbHandler.addDate(date)

        // Printed in log which is printed if the complete execution is done.
        Log.e(
            "Date : ",
            "Added..."
        )
    }
}