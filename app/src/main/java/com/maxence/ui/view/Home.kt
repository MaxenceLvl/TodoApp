package com.maxence.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maxence.data.ToDo
import com.maxence.data.TodoViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeView(scaffoldState: ScaffoldState, navController: NavHostController, model: TodoViewModel) {
    val scope = rememberCoroutineScope()
    val list: List<ToDo> = model.todoList.observeAsState(listOf()).value
    var textState by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp,),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFFFFFFF),
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                ),
                value = textState,
                onValueChange = { textState = it },
                placeholder = {
                    Text(text = "Enter Your Notes")
                },
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        model.insert(
                            ToDo(
                                title = textState,
                                description = null,
                            )
                        )
                        scope.launch {
                            textState = ""
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Notes added",
                            )
                        }
                    }) {
                    Text(text = "Add Notes")
                }
                Button(onClick = {
                    model.clear()
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "All Notes deleted",
                        )
                    }
                }) {
                    Text(text = "Clear")
                }
            }
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