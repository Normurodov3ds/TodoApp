package uz.kotlinjon.todoapp

import android.app.Application
import androidx.room.Room
import uz.kotlinjon.todoapp.model.db.AppDataBase

private const val TABLE = "table_name"

object App : Application() {
    lateinit var roomDatabase: AppDataBase

    override fun onCreate() {
        super.onCreate()
        synchronized(Unit) {
            if (!::roomDatabase.isInitialized) {
                roomDatabase = Room.databaseBuilder(
                    applicationContext,
                    AppDataBase::class.java, TABLE
                    // megratsiya uchun fun
                ).fallbackToDestructiveMigration().build()
            }
        }
    }
}