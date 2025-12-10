package com.enrique.hdbwandroid.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getTasks(userName: String): Flow<List<Task>> {
        return taskDao.getTasks(userName)
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun modify(task: Task) {
        taskDao.modify(task)
    }
}
