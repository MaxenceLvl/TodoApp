package com.maxence.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxence.data.ToDo
import com.maxence.data.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TodoItem(toDo: ToDo, scope: CoroutineScope, model: TodoViewModel) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${toDo.id}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = " : ${toDo.title}",
            )
            IconButton(onClick = {
                model.update(toDo)
                scope.launch {
                    Toast.makeText(ctx, "Notes updated id : ${toDo.id}",Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(Icons.Filled.Edit, "", tint = Color.Magenta)
            }
            IconButton(onClick = {
                model.delete(toDo)
                scope.launch {
                    Toast.makeText(ctx, "Notes deleted id : ${toDo.id}",Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(Icons.Filled.Delete, "", tint = Color.Red)
            }
        }
    }
}