package uz.kotlinjon.todoapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.kotlinjon.todoapp.model.repository.home.HomeRepository
import uz.kotlinjon.todoapp.model.repository.home.HomeRepositoryImpl

class HomeViewModel : ViewModel(), HomeVMC {
    private val _uiState = MutableStateFlow<UiEvents>(UiEvents.Loading)
    private val repository: HomeRepository = HomeRepositoryImpl()

    override fun getEvent() = _uiState.asStateFlow()
    override fun deleteTask(it: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTaskById(it)
        }
    }

    override fun getAllTaskFromData() {
        viewModelScope.launch {
            repository.getAllTask()
                .onEach {
                _uiState.value = UiEvents.Success(it)
            }.launchIn(this)
        }
    }




}