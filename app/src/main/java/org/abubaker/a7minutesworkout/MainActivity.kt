package org.abubaker.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.abubaker.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityMainBinding

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        // Start Button = Toast
        binding.llStart.setOnClickListener {
            // Toast.makeText(this, "Here we will start the exercise", Toast.LENGTH_SHORT).show()
            /**
             * ::class.java = getClass()
             * To retrieve the Java Class of an object, use the JAVA extension property on a class reference
             * Example: val fooClass = foo::class.java
             */
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

    }

}