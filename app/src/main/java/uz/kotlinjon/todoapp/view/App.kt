package uz.kotlinjon.todoapp.view

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import uz.kotlinjon.todoapp.model.db.AppDataBase

const val TABLE = "table_name"

class App : Application() {

    companion object{
        private  var roomDatabase: AppDataBase? =null
    }

    @SuppressLint("SuspiciousIndentation")
    fun getIn(): AppDataBase {
        if (roomDatabase == null)
            roomDatabase = Room.databaseBuilder(
                context = applicationContext,
                AppDataBase::class.java,
                TABLE
            ).build()

            return roomDatabase!!
    }

    override fun onCreate() {
        super.onCreate()
        getIn()
    }
}