package com.maxence.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getTodos(): LiveData<MutableList<ToDo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: Long): ToDo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo:ToDo)

    @Query("UPDATE todo SET isComplete = :isComplete WHERE id = :id")
    suspend fun update(id: Long, isComplete: Boolean)

    @Delete
    suspend fun delete(todo:ToDo)

    @Query("DELETE FROM todo")
    suspend fun clear()
}