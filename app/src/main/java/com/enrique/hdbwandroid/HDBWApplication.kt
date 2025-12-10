package com.enrique.hdbwandroid

import android.app.Application
import com.enrique.hdbwandroid.data.TaskDatabase
import com.enrique.hdbwandroid.data.TaskRepository
import com.enrique.hdbwandroid.datastore.UserPreferences

class HDBWApplication : Application() {
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
    val userPreferences by lazy { UserPreferences(this) }
}
