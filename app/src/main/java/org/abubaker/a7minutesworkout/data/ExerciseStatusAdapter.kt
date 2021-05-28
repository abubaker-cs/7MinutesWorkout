package org.abubaker.a7minutesworkout.data

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.bind(model)
    }

    /**
     *
     */
    class ViewHolder(private val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ExerciseModel) {
            binding.tvItem.text = model.getId().toString()
        }
    }

    /**
     *
     */
    override fun getItemCount(): Int {
        return items.size
    }

}