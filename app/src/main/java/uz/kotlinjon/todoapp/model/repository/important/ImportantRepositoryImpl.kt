package uz.kotlinjon.todoapp.model.repository.important

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.kotlinjon.todoapp.App
import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.model.toTaskModel

class ImportantRepositoryImpl : ImportantRepository {
    override suspend fun getImportantTasks(): Flow<List<TaskModel>> {
        return App.roomDatabase.getDao().getImportantTasks().map {
            it.map { item ->
                item.toTaskModel()
            }
        }
    }
}