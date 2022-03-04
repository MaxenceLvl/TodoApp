package com.maxence

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxence.data.ToDoViewModelFactory
import com.maxence.data.TodoViewModel
import com.maxence.ui.theme.TodoAppTheme
import com.maxence.ui.view.DetailView
import com.maxence.ui.view.GetScaffold

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val model: TodoViewModel = viewModel(
        factory = ToDoViewModelFactory(
            context.applicationContext as Application
        )
    )


    TodoAppTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { GetScaffold(navController, model) }
            composable("todo/{id}") { backStackEntry ->
                val todoId = backStackEntry.arguments?.getString("id")
                requireNotNull(todoId) { "todoId parameter wasn't found. Please make sure it's set!" }
                DetailView(todoId, model)
            }
//            composable(Destinations.AddTodo) { AddTodoView(navController) }
        }
    }
}