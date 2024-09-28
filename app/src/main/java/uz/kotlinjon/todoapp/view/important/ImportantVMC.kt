package uz.kotlinjon.todoapp.view.important

import kotlinx.coroutines.flow.StateFlow
import uz.kotlinjon.todoapp.view.home.UiEvents

interface ImportantVMC {
    fun getAllTaskFromData()
    fun getEvent(): StateFlow<UiEvents>
}