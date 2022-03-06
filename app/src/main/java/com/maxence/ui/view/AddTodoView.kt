package com.maxence.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maxence.data.ToDo
import com.maxence.data.TodoViewModel

@Composable
fun AddToDoView(navController: NavHostController, model: TodoViewModel) {
    var textStateTitle by remember { mutableStateOf("") }
    var textStateDescription by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 8.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
            ),
            value = textStateTitle,
            onValueChange = { textStateTitle = it },
            placeholder = {
                Text(text = "Enter Your Title")
            },
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
            ),
            value = textStateDescription,
            onValueChange = { textStateDescription = it },
            placeholder = {
                Text(text = "Enter Your Description")
            },
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ButtonComponent("Add Notes") {
                model.insert(
                    ToDo(
                        title = textStateTitle,
                        description = textStateDescription,
                    )
                )
                navController.popBackStack()
            }
            ButtonComponent("Clear") {
                textStateTitle = ""
                textStateDescription = ""
            }
        }
    }
}