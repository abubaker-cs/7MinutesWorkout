package org.abubaker.a7minutesworkout.data

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.a7minutesworkout.databinding.ItemExerciseStatusBinding

// We want to display ArrayList of ExerciseModel.kt file in data folder with a context (the active screen)
class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /**
     * 01 - onCreateViewHolder() - It will be called one the ViewHolder is created (when the screen is displayed)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Get reference to the UI Element using databinding
        val binding =
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // and pass it to ViewHolder()
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
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get reference to the current / looped item in the list
        val model: ExerciseModel = items[position]

        // Bind it to the holder
        holder.bind(model)
    }

    /**
     * ViewHolder() -
     */
    class ViewHolder(private val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Binding initiated
        fun bind(model: ExerciseModel) {
            binding.tvItem.text = model.getId().toString()
        }

    }


}