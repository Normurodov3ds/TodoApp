package uz.kotlinjon.todoapp.view.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.kotlinjon.todoapp.model.TaskModel
import uz.kotlinjon.todoapp.model.repository.add.AddTaskRepository
import uz.kotlinjon.todoapp.model.repository.add.AddTaskRepositoryImp

class AddTaskViewModel : ViewModel(), AddTaskVMC {

    private val addTaskRepository: AddTaskRepository = AddTaskRepositoryImp()


    override fun insertData(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskRepository.insertTaskModel(taskModel)
        }
    }
}