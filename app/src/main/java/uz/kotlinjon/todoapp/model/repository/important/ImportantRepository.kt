package uz.kotlinjon.todoapp.model.repository.important

import kotlinx.coroutines.flow.Flow
import uz.kotlinjon.todoapp.model.TaskModel

interface ImportantRepository {
    suspend fun getImportantTasks(): Flow<List<TaskModel>>
}