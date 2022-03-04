package com.maxence.ui.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.maxence.data.TodoViewModel

@Composable
fun GetScaffold(navController: NavHostController, model: TodoViewModel) {
    val scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = SnackbarHostState()
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ToDo Application",color= Color.White)
                        },
                backgroundColor = Color(0xFFFDA433),
            )
        },
        content = { HomeView(scaffoldState, navController, model) },
        backgroundColor = Color(0xFFBEEFF5),
    )
}