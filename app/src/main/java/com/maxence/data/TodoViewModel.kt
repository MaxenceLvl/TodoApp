package com.maxence.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application:Application):ViewModel() {
    private val db = RoomSingleton.getInstance(application)

    internal val todoList:LiveData<MutableList<ToDo>> = db.todoDao().getTodos()

    fun insert(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().insert(todo)
        }
    }

    fun getById(todoId: Long): ToDo? {
        var todo: ToDo? = null
        viewModelScope.launch(Dispatchers.IO) {
            todo = db.todoDao().getById(todoId)
        }
        return todo
    }

    fun update(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().update(todo.id, !todo.isComplete)
        }
    }

    fun delete(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().delete(todo)
        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().clear()
        }
    }
}