package uz.kotlinjon.todoapp.model.repository.add

import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.model.toEntity
import uz.kotlinjon.todoapp.view.App

class AddTaskRepositoryImp : AddTaskRepository {

    override suspend fun insertTaskModel(taskModel: TaskModel) {
        App().getIn().getDao()
            .insertAll(
                taskModel.toEntity()
            )
    }
}