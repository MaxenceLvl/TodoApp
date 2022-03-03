package com.maxence.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class RoomSingleton : RoomDatabase() {
    abstract fun todoDao():TodoDAO

    companion object {
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as RoomSingleton
        }
    }
}