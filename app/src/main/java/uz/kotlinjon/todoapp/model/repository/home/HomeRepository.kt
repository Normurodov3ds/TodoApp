package uz.kotlinjon.todoapp.model.repository.home

import kotlinx.coroutines.flow.Flow
import uz.kotlinjon.todoapp.model.TaskModel

interface HomeRepository {
    suspend fun getAllTask(): Flow<List<TaskModel>>
    suspend fun deleteTaskById(it: Int)
}