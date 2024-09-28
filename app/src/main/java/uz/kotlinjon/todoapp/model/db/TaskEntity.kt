package uz.kotlinjon.todoapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "Title")
    val title: String?,
    @ColumnInfo(name = "Disc")
    val disc: String?,
    @ColumnInfo(name = "Data")
    val data: String?,
    @ColumnInfo(name = "Important")
    val isImportant: Boolean = false
)