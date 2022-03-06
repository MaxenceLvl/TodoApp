package com.maxence.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonComponent(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(50),
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color(0xFFE05657)
                )
                .clickable(onClick = { onClick() })
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.toUpperCase(Locale.current),
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
            )
        }
    }
}