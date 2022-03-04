package com.maxence.ui.view

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.maxence.data.TodoViewModel

@Composable
fun DetailView(todoId: String, viewModel: TodoViewModel) {
    val todo = viewModel.todoList.value?.first { it.id == todoId.toLong() }
    Scaffold() {
        
    }
    TopAppBar(
        title = {
            Text(text = "ToDo Application",color= Color.White)
        },
        backgroundColor = Color(0xFFFDA433),
    )
}