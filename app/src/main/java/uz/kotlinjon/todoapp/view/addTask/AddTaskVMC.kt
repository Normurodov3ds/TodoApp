package uz.kotlinjon.todoapp.view.addTask

import uz.kotlinjon.todoapp.model.TaskModel

interface AddTaskVMC {
    fun insertData(taskModel: TaskModel)
}