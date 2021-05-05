package org.abubaker.a7minutesworkout.data

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {

    /**
     * 1. id
     */
    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    /**
     * 2. name
     */
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    /**
     * 3. image
     */
    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    /**
     * 4. isCompleted
     */
    fun getIsCompleted(): Boolean {
        return isCompleted
    }

    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    /**
     * 5. isSelected
     */
    fun getIsSelected(): Boolean {
        return isSelected
    }

    fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }

}