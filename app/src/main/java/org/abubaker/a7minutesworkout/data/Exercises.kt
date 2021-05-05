package org.abubaker.a7minutesworkout.data

import org.abubaker.a7minutesworkout.R

class Exercises {

    // It is like static in other languages
    companion object {

        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            // Array
            val exerciseList = ArrayList<ExerciseModel>()

            // EX 1 - jumpingJacks
            val jumpingJacks =
                ExerciseModel(
                    1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks,
                    false,
                    false
                )
            exerciseList.add(jumpingJacks)

            // EX 2 - wallSit
            val wallSit = ExerciseModel(
                2,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,
                false
            )
            exerciseList.add(wallSit)

            // EX 3 - pushUp
            val pushUp = ExerciseModel(
                3,
                "Push Up",
                R.drawable.ic_push_up,
                false,
                false
            )
            exerciseList.add(pushUp)

            // EX 4 - abdominalCrunch
            val abdominalCrunch =
                ExerciseModel(
                    4,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch,
                    false,
                    false
                )
            exerciseList.add(abdominalCrunch)

            // EX 5 - stepUpOnChair
            val stepUpOnChair = ExerciseModel(
                5,
                "Step-Up onto Chair",
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            )
            exerciseList.add(stepUpOnChair)

            // EX 6 -
            val squat = ExerciseModel(
                6,
                "Squat",
                R.drawable.ic_squat,
                false,
                false
            )
            exerciseList.add(squat)

            // EX 7 -
            val tricepDipOnChair =
                ExerciseModel(
                    7,
                    "Tricep Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false
                )
            exerciseList.add(tricepDipOnChair)

            // EX 8 - plank
            val plank = ExerciseModel(
                8,
                "Plank",
                R.drawable.ic_plank,
                false,
                false
            )
            exerciseList.add(plank)

            // EX 9 - highKneesRunningInPlace
            val highKneesRunningInPlace =
                ExerciseModel(
                    9, "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            // EX 10 - lunges
            val lunges = ExerciseModel(
                10,
                "Lunges",
                R.drawable.ic_lunge,
                false,
                false
            )
            exerciseList.add(lunges)

            // EX 11 - pushupAndRotation
            val pushupAndRotation =
                ExerciseModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false
                )
            exerciseList.add(pushupAndRotation)

            // EX 12 - sidePlank
            val sidePlank = ExerciseModel(
                12,
                "Side Plank",
                R.drawable.ic_side_plank,
                false,
                false
            )
            exerciseList.add(sidePlank)

            // Return all exercises
            return exerciseList
        }
    }
}