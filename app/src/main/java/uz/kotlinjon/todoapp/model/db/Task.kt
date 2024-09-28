package uz.kotlinjon.todoapp.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Task {

    @Query("SELECT * FROM taskentity")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskentity Where Important == 1 ")
    fun getImportantTasks(): Flow<List<TaskEntity>>

    @Insert
    fun insertAll(vararg users: TaskEntity)

    @Query("DELETE FROM taskentity WHERE id = :id")
    fun delete(id:Int)
}