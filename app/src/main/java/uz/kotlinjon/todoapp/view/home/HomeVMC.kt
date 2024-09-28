package uz.kotlinjon.todoapp.view.home

import kotlinx.coroutines.flow.StateFlow

interface HomeVMC {
    fun getAllTaskFromData()
    fun getEvent(): StateFlow<UiEvents>
    fun deleteTask(it: Int)
}