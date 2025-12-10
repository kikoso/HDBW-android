package com.enrique.hdbwandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.enrique.hdbwandroid.datastore.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    val userName: StateFlow<String?> = userPreferences.userName
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPreferences.saveUserName(name)
        }
    }
}

class MainViewModelFactory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
