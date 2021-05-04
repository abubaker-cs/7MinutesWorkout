package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityExerciseBinding

    //
    private var restTimer: CountDownTimer? = null

    // It will just count up, instead of counting down. i.e. 0...10
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ExerciseActivity, R.layout.activity_exercise)

        setSupportActionBar(binding.toolbarExerciseActivity)

        val actionbar = supportActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        // Back Button in the Action Bar (TOP)
        binding.toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        //
        setupRestView()


    }

    private fun setRestProgressBar() {

        // = 0
        binding.progressBar.progress = restProgress

        // 10sec - 1sec
        restTimer = object : CountDownTimer(10000, 1000) {

            // On every single tick
            override fun onTick(millisUntilFinished: Long) {
                restProgress++

                // It will decrease values
                binding.progressBar.progress = 10 - restProgress

                binding.tvTimer.text = (10 - restProgress).toString()

            }

            // Once Finished | After 10 seconds
            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here now we will start the exercise.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()
    }

    override fun onDestroy() {

        if (restTimer != null) {

            // if restTimer is not null, then set it to cancel
            restTimer!!.cancel()

            // Reset Progress to 0
            restProgress = 0

        }

        super.onDestroy()
    }

    /**
     *
     */
    private fun setupRestView() {
        if (restTimer != null) {

            // rest it
            restTimer!!.cancel()

            // Put it back to zero
            restProgress = 0

            // initiate this function
            setRestProgressBar()
        }
    }

}