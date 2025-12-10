package com.enrique.hdbwandroid.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun modify(task: Task)

    @Query("SELECT * FROM tasks WHERE userName = :userName")
    fun getTasks(userName: String): Flow<List<Task>>
}
