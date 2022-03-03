package com.maxence.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getTodos(): LiveData<MutableList<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo:ToDo)

    @Update
    suspend fun update(todo:ToDo)

    @Delete
    suspend fun delete(todo:ToDo)

    @Query("DELETE FROM todo")
    suspend fun clear()
}