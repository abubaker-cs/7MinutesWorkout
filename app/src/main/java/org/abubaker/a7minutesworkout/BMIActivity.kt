package org.abubaker.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityBmiBinding

    // Variables for Unit Systems
    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BMIActivity, R.layout.activity_bmi)

        // Enable ActionBar using setSupportActionBar()
        setSupportActionBar(binding.toolbarBmiActivity)

        // Assigning recently enabled ActionBar to the variable, using supportActionBar
        val actionbar = supportActionBar

        if (actionbar != null) {
            // Enable back Button
            actionbar.setDisplayHomeAsUpEnabled(true)

            // Changing the default title
            actionbar.title = "CALCULATE ME"
        }

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            // Redirect to the HOME Screen
            onBackPressed()
        }

        // Button: Calculate Units
        binding.btnCalculateUnits.setOnClickListener {
            // The values are validated.
            if (validateMetricUnits()) {

                // 1. Height (convert to float value) / 100 = Meter
                val heightValue: Float = binding.etMetricUnitHeight.text.toString().toFloat() / 100

                // 2. Weight (convert to float value)
                val weightValue: Float = binding.etMetricUnitWeight.text.toString().toFloat()

                // 3. Formula: BMI value is calculated in METRIC UNITS using the height and weight value.
                val bmi = weightValue / (heightValue * heightValue)

                // 4. Function: Calculate BMI Range and Display Summary
                displayBMIResult(bmi)

            } else {
                // Error: Inform the user to provide VALID values
                Toast.makeText(this@BMIActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    /**
     * Validate the input values for METRIC UNITS.
     */
    private fun validateMetricUnits(): Boolean {

        // Default value
        var isValid = true

        // Are Weight and Height fields EMPTY?
        if (binding.etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }

        // Return result
        return isValid
    }

    /**
     * It will take care of Visibility for the Unit Systems
     */
    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW

        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitHeight.visibility = View.VISIBLE

        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitWeight.text!!.clear()

        binding.llUsUnitsHeight.visibility = View.GONE
        binding.tilUsUnitWeight.visibility = View.GONE

        binding.llDisplayBMIResult.visibility = View.GONE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW

        binding.etUsUnitWeight.text!!.clear()
        binding.etUsUnitHeightFeet.text!!.clear()
        binding.etUsUnitHeightInch.text!!.clear()

        binding.tilMetricUnitWeight.visibility = View.GONE
        binding.tilMetricUnitHeight.visibility = View.GONE

        binding.llUsUnitsHeight.visibility = View.VISIBLE
        binding.tilUsUnitWeight.visibility = View.VISIBLE

        binding.llDisplayBMIResult.visibility = View.GONE
    }


    /**
     * Display the result of METRIC UNITS.
     */
    private fun displayBMIResult(bmi: Float) {

        // Declare variables
        val bmiLabel: String
        val bmiDescription: String

        // When = Switch Statement
        when {

            // compareTo():
            //      0 = EQUAL
            //      -ve = Less than
            //      +ve = Greater than

            // BMI Range: < 15
            bmi.compareTo(15f) <= 0 -> {
                bmiLabel = "Very severely underweight"
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            }

            // BMI Range: 15 - 16
            bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0 -> {
                bmiLabel = "Severely underweight"
                bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
            }

            // BMI Range: 16 - 18.5
            bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0 -> {
                bmiLabel = "Underweight"
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            }

            // BMI Range: 18.5 - 25
            bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0 -> {
                bmiLabel = "Normal"
                bmiDescription = "Congratulations! You are in a good shape!"
            }

            // BMI Range: 25 - 30
            bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0 -> {
                bmiLabel = "Overweight"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }

            // BMI Range: 30 - 35
            bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0 -> {
                bmiLabel = "Obese Class 1 - (Moderately obese)"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }

            // BMI Range: 35 - 40
            bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0 -> {
                bmiLabel = "Obese Class 2 - (Severely obese)"
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            }

            // BMI Range: 40+
            else -> {
                bmiLabel = "Obese Class 3 - (Very Severely obese)"
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            }

        }

        // Toggle hidden state to Visible for following components:
        binding.llDisplayBMIResult.visibility = View.VISIBLE
        // binding.tvYourBMI.visibility = View.VISIBLE
        // binding.tvBMIValue.visibility = View.VISIBLE
        // binding.tvBMIType.visibility = View.VISIBLE
        // binding.tvBMIDescription.visibility = View.VISIBLE

        // BMI Value: Round the result to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        // Populate values so the user can see details
        binding.tvBMIValue.text = bmiValue // BMI Value
        binding.tvBMIType.text = bmiLabel // Label
        binding.tvBMIDescription.text = bmiDescription // Description

    }
}