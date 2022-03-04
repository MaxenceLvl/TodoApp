package com.maxence

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.maxence.ui.view.MainContent

@Composable
fun GetScaffold(){
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
        content = { MainContent(scaffoldState) },
        backgroundColor = Color(0xFFBEEFF5),
    )
}