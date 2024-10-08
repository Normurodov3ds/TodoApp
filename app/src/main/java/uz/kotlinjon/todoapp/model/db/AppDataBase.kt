package uz.kotlinjon.todoapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): Task

}