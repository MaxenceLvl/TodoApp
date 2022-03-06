package com.maxence.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maxence.data.ToDo
import com.maxence.data.TodoViewModel

@Composable
fun HomeView(navController: NavHostController, model: TodoViewModel) {
    val scope = rememberCoroutineScope()
    val list: List<ToDo> = model.todoList.observeAsState(listOf()).value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                val sortedList = list.sortedWith(compareBy { it.isComplete })
                sortedList.forEachIndexed { index, toDo ->
                    TodoItem(toDo, scope, model) {
                        navController.navigate("todo/${toDo.id}")
                    }
                }
            }
        }
    }
}