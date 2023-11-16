package com.joel.jetquiz.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joel.jetquiz.ui.theme.blck3
import com.joel.jetquiz.ui.theme.grn3
import com.joel.jetquiz.ui.theme.wht3

@Composable
fun ReusableButtonCard(
    title : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    selected : Boolean = false
){

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        modifier = modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (selected) grn3 else blck3
        )
    ) {
        Text(
            text = title,
            color = wht3,
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun OutlinedActionButtonCard(
    title : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
){

    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .height(50.dp)
    ) {
       Text(text = title)
    }

}
