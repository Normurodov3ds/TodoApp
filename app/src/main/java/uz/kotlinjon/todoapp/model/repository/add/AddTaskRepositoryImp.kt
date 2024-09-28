package uz.kotlinjon.todoapp.model.repository.add

import uz.kotlinjon.todoapp.App
import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.model.toEntity

class AddTaskRepositoryImp : AddTaskRepository {

    override suspend fun insertTaskModel(taskModel: TaskModel) {
        App.roomDatabase.getDao()
            .insertAll(
                taskModel.toEntity()
            )
    }
}