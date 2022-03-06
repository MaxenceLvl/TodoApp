package com.maxence.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxence.data.TodoViewModel

@Composable
fun DetailView(todoId: String, viewModel: TodoViewModel) {
    val todo = viewModel.todoList.value?.first { it.id == todoId.toLong() }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color(0xFF456d82).copy(alpha = 0.3f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title : ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 6.dp, end = 2.dp)
                    .padding(vertical = 6.dp)
            )
            Text(
                text = todo!!.title,
                fontSize = 14.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color(0xFF456d82).copy(alpha = 0.3f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Description : ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 6.dp, end = 2.dp)
                    .padding(vertical = 6.dp)
            )
            Text(
                text = if (todo?.description != null) todo.description else "No description",
                fontSize = 14.sp,
            )
        }
    }
}