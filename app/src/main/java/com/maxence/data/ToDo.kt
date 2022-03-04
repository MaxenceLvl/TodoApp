package com.maxence.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDo(
    val title: String,
    val description: String?,
    var isComplete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0)