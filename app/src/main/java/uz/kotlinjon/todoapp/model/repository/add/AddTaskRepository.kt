package uz.kotlinjon.todoapp.model.repository.add

import uz.kotlinjon.todoapp.model.TaskModel

interface AddTaskRepository {
    suspend fun insertTaskModel(taskModel: TaskModel)
}