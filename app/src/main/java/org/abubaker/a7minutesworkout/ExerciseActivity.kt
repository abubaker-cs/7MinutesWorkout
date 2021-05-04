package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityExerciseBinding

    // Variable for Rest Timer and later on we will initialize it.
    private var restTimer: CountDownTimer? = null

    // Variable for timer progress. As initial value the rest progress is set to 0. As we are about to start.
    private var restProgress = 0
    private var restTimerDuration: Long = 10


    // For Exercise (Challenge)
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration: Long = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ExerciseActivity, R.layout.activity_exercise)

        setSupportActionBar(binding.toolbarExerciseActivity)

        val actionbar = supportActionBar


        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        // Navigate the activity on click on back button of action bar.
        binding.toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        // Calling the function to make it visible on screen
        setupRestView()

    }

    /**
     * setupRestView() -
     */
    private fun setupRestView() {

        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (restTimer != null) {

            // rest it
            restTimer!!.cancel()

            // Put it back to zero
            restProgress = 0

        }

        // This function is used to set the progress details.
        setRestProgressBar()
    }

    /**
     * Function is used to set the progress of timer using the progress
     * Setting up the 10 seconds timer for rest view and updating it continuously
     */
    private fun setRestProgressBar() {

        // Sets the current progress to the specified value = 0
        binding.progressBar.progress = restProgress

        // 10sec - 1sec
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {

            // On every single tick
            override fun onTick(millisUntilFinished: Long) {

                // It is increased by 1
                restProgress++

                // Indicates progress bar progress | It will decrease values in the circular progress bar
                binding.progressBar.progress = restTimerDuration.toInt() - restProgress

                // Current progress is set to text view in terms of seconds.
                binding.tvTimer.text = (restTimerDuration.toInt() - restProgress).toString()

                Log.i("millisUntilFinished ", millisUntilFinished.toString())

            }

            // Once Finished | After 10 seconds
            override fun onFinish() {

                // Initialize the Exercise View
                setupExerciseView()

            }

        }.start()
    }

    // Destroying the timer when closing the activity or app.
    public override fun onDestroy() {

        if (restTimer != null) {

            // if restTimer is not null, then set it to cancel
            restTimer!!.cancel()

            // Reset Progress to 0
            restProgress = 0

        }

        super.onDestroy()
    }


    /**
     * Code for Exercise Challenge
     */

    private fun setupExerciseView() {

        binding.llRestView.visibility = View.GONE
        binding.llExerciseView.visibility = View.VISIBLE

        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (exerciseTimer != null) {

            // rest it
            exerciseTimer!!.cancel()

            // Put it back to zero
            exerciseProgress = 0

        }

        // This function is used to set the progress details.
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {

        // Sets the current progress to the specified value = 0
        binding.progressBarExercise.progress = exerciseProgress

        // 10sec - 1sec
        // Here we have started a timer of 10 seconds so the 30000 is milliseconds is 30 seconds and the countdown interval is 1 second so it 1000.
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {

            // On every single tick
            override fun onTick(millisUntilFinished: Long) {

                // It is increased by 1
                exerciseProgress++

                // Indicates progress bar progress | It will decrease values in the circular progress bar
                binding.progressBarExercise.progress =
                    exerciseTimerDuration.toInt() - exerciseProgress

                // Current progress is set to text view in terms of seconds.
                binding.tvExerciseTimer.text =
                    (exerciseTimerDuration.toInt() - exerciseProgress).toString()

            }

            // Once Finished | After 10 seconds
            override fun onFinish() {

                // When the 30 seconds will complete this will be executed.
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here we will start the next rest timer.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }.start()
    }

}