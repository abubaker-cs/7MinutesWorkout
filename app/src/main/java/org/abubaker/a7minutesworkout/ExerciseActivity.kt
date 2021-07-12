package org.abubaker.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.abubaker.a7minutesworkout.data.ExerciseModel
import org.abubaker.a7minutesworkout.data.ExerciseStatusAdapter
import org.abubaker.a7minutesworkout.data.Exercises
import org.abubaker.a7minutesworkout.databinding.ActivityExerciseBinding
import org.abubaker.a7minutesworkout.databinding.ActivityFinishBinding
import org.abubaker.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    // Binding Object
    private lateinit var binding: ActivityExerciseBinding

    // Variable for Rest Timer and later on we will initialize it.
    private var restTimer: CountDownTimer? = null

    // Variable for timer progress. As initial value the rest progress is set to 0. As we are about to start.
    private var restProgress = 0
    private var restTimerDuration: Long = 1 // 10 - Being used in setRestProgressBar()

    // For Exercise (Challenge)
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration: Long = 1 // 30

    //
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    // For Text To Speech
    private var tts: TextToSpeech? = null

    // Media Player
    private var player: MediaPlayer? = null

    // Adapter
    private var exerciseAdapter: ExerciseStatusAdapter? = null


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

            // onBackPressed()

            customDialogForBackButton()
        }

        // It will fetch all data from the Exercises Array
        exerciseList = Exercises.defaultExerciseList()

        // initialize TTS
        tts = TextToSpeech(this, this)

        // Calling the function to make it visible on screen
        setupRestView()

        //
        // exerciseList = Constants.defaultExerciseList()

        //
        setupExerciseStatusRecyclerView()

    }

    /**
     * setupRestView() -
     */
    private fun setupRestView() {

        try {

            // OLD METHOD:
            // val soundURI = Uri.parse("android.resource://org.abubaker.a7minutesworkout/" + R.raw.press_start)

            // NEW METHOD:
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false // only play once
            player!!.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }


        // Visibility States
        binding.llRestView.visibility = View.VISIBLE
        binding.llExerciseView.visibility = View.GONE

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

        // Sets the name of next exercise
        binding.tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

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

            }

            // Once Finished | After 10 seconds
            override fun onFinish() {

                // Increases the current exercise position by 1
                currentExercisePosition++

                // Set current position to be selected
                exerciseList!![currentExercisePosition].setIsSelected(true)

                // Recalculate view on the data change
                exerciseAdapter!!.notifyDataSetChanged()

                // Initialize the Exercise View
                setupExerciseView()

            }

        }.start()
    }

    // Destroying the timer when closing the activity or app.
    public override fun onDestroy() {

        // Rest Timer
        if (restTimer != null) {

            // if restTimer is not null, then set it to cancel
            restTimer!!.cancel()

            // Reset Progress to 0
            restProgress = 0

        }

        // Exercise Timer
        if (exerciseTimer != null) {

            // if restTimer is not null, then set it to cancel
            exerciseTimer!!.cancel()

            // Reset Progress to 0
            exerciseProgress = 0

        }

        // TTS
        if (tts != null) {

            // Stop it
            tts!!.stop()

            // Then shut it down
            tts!!.shutdown()

        }

        // Player
        if (player != null) {
            player!!.stop()
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

        // TTS: Speak the name of the exercise
        speakOut(exerciseList!![currentExercisePosition].getName())

        // This function is used to set the progress details.
        setExerciseProgressBar()
        binding.ivThumbnail.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

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

            // Check if all exercises are completed?
            override fun onFinish() {

                // If there are more exercises to be completed:
                // FOR TESTING ONLY: if (currentExercisePosition < 2) {
                if (currentExercisePosition < exerciseList?.size!! - 1) {

                    // Make sure that the finished exercise is:
                    // set not active, is completed and notify data changes
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()

                    setupRestView()

                } else {
                    // If all exercises are completed:

                    // It is important, because if we will press the BACK button then we will not
                    // be sent back to the exercise counter, rather we will see HOME screen
                    finish()

                    /**
                     * ::class.java = getClass()
                     * To retrieve the Java Class of an object, use the JAVA extension property on a class reference
                     * Example: val fooClass = foo::class.java
                     */
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)

                    // Toast.makeText(
                    //    this@ExerciseActivity,
                    //    "Congratulations! You have completed the 7 minutes workout.",
                    //    Toast.LENGTH_SHORT
                    // ).show()
                }

            }

        }.start()
    }

    /**
     * onInit()
     */
    override fun onInit(status: Int) {

        // If TTS works
        if (status == TextToSpeech.SUCCESS) {

            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            // If the language is not supported
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    //
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //
    private fun setupExerciseStatusRecyclerView() {

        //
        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)

        //
        binding.rvExerciseStatus.adapter = exerciseAdapter

    }

    /**
     * Function is used to launch the custom confirmation dialog.
     */
    // Performing the steps to show the custom dialog for back button confirmation while the exercise is going on.
    private fun customDialogForBackButton() {

        val customDialog = Dialog(this@ExerciseActivity)

        val binding: DialogCustomBackConfirmationBinding =
            DialogCustomBackConfirmationBinding.inflate(layoutInflater)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        customDialog.setContentView(binding.root)

        binding.tvYes.setOnClickListener(View.OnClickListener
        {
            finish() // It will move to the MAIN Activity
            customDialog.dismiss() // Dialog will be dismissed
        })

        binding.tvNo.setOnClickListener(View.OnClickListener
        {
            // It will only close the dialog
            customDialog.dismiss()
        })

        //Start the dialog and display it on screen.
        customDialog.show()
    }


}