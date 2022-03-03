package com.maxence.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class ToDo(
    val title: String,
    val description: String?,
    val isComplete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0) {

}