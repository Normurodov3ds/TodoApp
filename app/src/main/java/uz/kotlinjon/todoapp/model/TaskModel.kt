package uz.kotlinjon.todoapp.model

import uz.kotlinjon.todoapp.model.db.TaskEntity

// ui model
data class TaskModel(
    val id: Int = 0,
    val title: String,
    val disc: String,
    val data: String
) {
    private var isImportant: Boolean = false

    fun changeImportant(important: Boolean) {
        isImportant = important
    }

    fun isImportant(): Boolean = isImportant
}

// Extension
fun TaskModel.toEntity(): TaskEntity {
    return TaskEntity(
        title = this.title,
        disc = this.disc,
        data = this.data,
        isImportant = this.isImportant()
    )
}

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        id = this.id,
        disc = this.disc ?: "",
        data = this.data ?: "",
        title = this.title ?: "",
    ).apply {
        this.changeImportant(this@toTaskModel.isImportant)
    }
}