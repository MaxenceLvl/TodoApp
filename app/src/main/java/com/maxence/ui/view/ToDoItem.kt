package com.maxence.ui.view

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxence.data.ToDo
import com.maxence.data.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TodoItem(toDo: ToDo, scope: CoroutineScope, model: TodoViewModel, onClick: () -> Unit) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(42.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = if (toDo.isComplete) Color.LightGray else Color.Transparent),
            contentAlignment = Alignment.CenterStart) {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = toDo.title,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = if (toDo.isComplete) 4.dp else 0.dp)
                        .weight(0.8f)
                )
                Checkbox(
                    checked = toDo.isComplete,
                    onCheckedChange = {
                        println(toDo)
                        model.update(toDo)
                        scope.launch {
                            Toast.makeText(
                                ctx,
                                "Notes updated id : ${toDo.id} -> ${toDo.isComplete}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                )
                IconButton(onClick = {
                    model.delete(toDo)
                    scope.launch {
                        Toast.makeText(ctx, "Notes deleted id : ${toDo.id}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                    Icon(Icons.Filled.Delete, "", tint = Color.Red)
                }
            }
//            if (toDo.isComplete) {
//                Canvas(modifier = Modifier
//                    .fillMaxSize()
//                    .align(Alignment.Center), onDraw = {
//                    drawLine(
//                        color = Color.Black,
//                        start = Offset(x = 0f, y = size.height / 2),
//                        end = Offset(x = size.width, y = size.height / 2),
//                        strokeWidth = 8f
//                    )
//                })
//            }
        }
    }
}