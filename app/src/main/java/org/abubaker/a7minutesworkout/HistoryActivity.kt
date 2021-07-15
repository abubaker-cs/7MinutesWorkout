package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.abubaker.a7minutesworkout.adapters.HistoryAdapter
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
        // for (i in allCompletedDatesList) {
        // Log.e("Date : ", "" + i)

        // }

        // Now here the dates which were printed in log.
        //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
        // START
        if (allCompletedDatesList.size > 0) {
            // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
            binding.tvHistory.visibility = View.VISIBLE
            binding.rvHistory.visibility = View.VISIBLE
            binding.tvNoDataAvailable.visibility = View.GONE

            // Creates a vertical Layout Manager
            binding.rvHistory.layoutManager = LinearLayoutManager(this)

            // History adapter is initialized and the list is passed in the param.
            val historyAdapter = HistoryAdapter(allCompletedDatesList)

            // Access the RecyclerView Adapter and load the data into it
            binding.rvHistory.adapter = historyAdapter
        } else {
            binding.tvHistory.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
            binding.tvNoDataAvailable.visibility = View.VISIBLE
        }

    }

}