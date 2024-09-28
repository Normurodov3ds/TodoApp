package uz.kotlinjon.todoapp.view.home

import uz.kotlinjon.todoapp.model.TaskModel

sealed interface UiEvents {
    data class Success(val list: List<TaskModel>) : UiEvents
    data object Loading : UiEvents
    data class Error(val massage: String) : UiEvents
}