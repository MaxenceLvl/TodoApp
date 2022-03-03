package com.maxence.ui.view

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxence.data.ToDo
import com.maxence.data.ToDoViewModelFactory
import com.maxence.data.TodoViewModel
import kotlinx.coroutines.launch

@Composable
fun MainContent(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val model: TodoViewModel = viewModel(
        factory = ToDoViewModelFactory(
            context.applicationContext as Application
        )
    )
    val list: List<ToDo> = model.todoList.observeAsState(listOf()).value
    var textState by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        //contentAlignment = Alignment.Center
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
                list.forEachIndexed { index, toDo ->
                    TodoItem(toDo, scope, model)
                }
            }
        }
    }
}