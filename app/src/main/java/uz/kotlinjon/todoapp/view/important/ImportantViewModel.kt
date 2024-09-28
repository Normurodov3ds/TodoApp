package uz.kotlinjon.todoapp.view.important

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.kotlinjon.todoapp.view.home.UiEvents
import uz.kotlinjon.todoapp.model.repository.home.HomeRepository
import uz.kotlinjon.todoapp.model.repository.home.HomeRepositoryImpl
import uz.kotlinjon.todoapp.model.repository.important.ImportantRepository
import uz.kotlinjon.todoapp.model.repository.important.ImportantRepositoryImpl

class ImportantViewModel : ViewModel() {
    private val repository: ImportantRepository = ImportantRepositoryImpl()
    private val homeRepository: HomeRepository = HomeRepositoryImpl()
    private val _uiState = MutableStateFlow<UiEvents>(UiEvents.Loading)

    fun getUiEvents() = _uiState.asStateFlow()

    init {
        getImportantTask()
    }

    private fun getImportantTask() {
        viewModelScope.launch {
            repository.getImportantTasks()
                .onEach {
                    _uiState.value = UiEvents.Success(it)
                }.launchIn(this)
        }
    }
     fun deleteTask(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.deleteTaskById(id)
        }
    }
}