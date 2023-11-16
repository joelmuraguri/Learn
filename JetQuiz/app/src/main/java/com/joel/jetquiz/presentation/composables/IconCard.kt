package com.joel.jetquiz.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joel.jetquiz.ui.theme.blck3
import com.joel.jetquiz.ui.theme.wht3

@Composable
fun IconCard(
    icon : Int,
    onClick : () -> Unit
){

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = blck3
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(2.dp)
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = wht3
                )
            }
        }
    }
}