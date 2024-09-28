package uz.kotlinjon.todoapp.model.repository.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.kotlinjon.todoapp.App
import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.model.toTaskModel


class HomeRepositoryImpl() : HomeRepository {
    override suspend fun getAllTask(): Flow<List<TaskModel>> {
        return App.roomDatabase.getDao().getAllTask().map {
            it.map { item ->
                item.toTaskModel()
            }
        }
    }

    override suspend fun deleteTaskById(it: Int) {
        App.roomDatabase.getDao().delete(id = it)
    }
}