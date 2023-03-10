package com.sukhralia.enjoin.feature.product.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sukhralia.enjoin.feature.product.domain.models.Item

@Preview
@Composable
fun ProductCard(
    item: Item = Item(
        "123",
        "123",
        "test",
        "123",
        "test"
    )
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(4.dp, Color.Black, shape = RoundedCornerShape(10.dp))
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            Text(
                text = item.name,
                color = Color.Black,
            )
        }
    }
}