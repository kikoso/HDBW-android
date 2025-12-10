package com.enrique.hdbwandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.enrique.hdbwandroid.data.Task
import com.enrique.hdbwandroid.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository, private val userName: String) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.getTasks(userName)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(description: String) {
        viewModelScope.launch {
            repository.insert(Task(description = description, userName = userName))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun modifyTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}

class TaskViewModelFactory(private val repository: TaskRepository, private val userName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository, userName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
