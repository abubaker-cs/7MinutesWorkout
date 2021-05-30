package org.abubaker.a7minutesworkout.data

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.a7minutesworkout.R
import org.abubaker.a7minutesworkout.databinding.ItemExerciseStatusBinding

// We want to display ArrayList of ExerciseModel.kt file in data folder with a context (the active screen)
class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /**
     * 01 - onCreateViewHolder() - It will be called one the ViewHolder is created (when the screen is displayed)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Get reference to the UI Element using databinding
        // ItemExerciseStatusBinding = item_exercise_status.xml file
        val binding =
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status, parent, false))

        // and pass it to our custom ViewHolder() declared as a class at the bottom of this file
        return ViewHolder(binding)
    }

    /**
     * 02 - getItemCount() -
     */
    override fun getItemCount(): Int {

        // Returns the total size of rows
        return items.size

    }

    /**
     * 03 - onBindViewHolder() - Binds each item in the ArrayList to a View
     * We want to assign text to every single circle, otherwise they will be empty
     * or they will just have 1
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Loop: Get the position of the item we are looking at
        val model: ExerciseModel = items[position]

        // Using our Custom ViewHolder, bind the selected item.position to the holder
        // holder.tvItem.text = model.getId().toString()

        // val context: Context = holder.itemView.getContext()

        holder.bind(model)

    }

    /**
     * 04 - ViewHolder() - Our Custom ViewHolder
     */
    class ViewHolder(private val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind item to the ExerciseModel.kt file
        fun bind(model: ExerciseModel) {

            // Using getID() method, declared in the ExerciseModel.kt file
            // bind the item.position to the tvItem TextView
            binding.tvItem.text = model.getId().toString()

            if (model.getIsSelected()) {

                // val context: Context = holder.itemView.getContext()

                // On Started: White Background
                binding.tvItem.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.item_circular_thin_color_accent_border
                )

                //
                binding.tvItem.setTextColor(Color.parseColor("#212121"))

            } else if (model.getIsCompleted()) {

                // On Completed: Green Background
                binding.tvItem.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.item_circular_color_accent_background
                )

                //
                binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            } else {

                // Default Color: Gray Background
                binding.tvItem.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.item_circular_color_gray_background
                )

                //
                binding.tvItem.setTextColor(Color.parseColor("#212121"))

            }
        }

        // holder.tvItem.text = model.getId().toString()

    }


}