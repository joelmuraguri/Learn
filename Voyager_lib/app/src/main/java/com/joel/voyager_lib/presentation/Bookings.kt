package com.joel.voyager_lib.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joel.voyager_lib.data.Bookings
import com.joel.voyager_lib.data.DataStore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bookings(
    toHome : () -> Unit,
    onItemClick: (Bookings) -> Unit
){
    
    val allBookings = DataStore.bookings

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = { toHome() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(12.dp)
            ){
                items(allBookings){bookings ->
                    ItemCard(
                        bookings = bookings,
                        onItemClick = { bookings ->
                            onItemClick(bookings)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun ItemCard(
    bookings: Bookings,
    onItemClick : (Bookings) -> Unit
){
    
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .clickable {
                onItemClick(bookings)
            }
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Session with ${bookings.name}",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(12.dp)
            )
            Text(
                "${bookings.name} is a ${bookings.title} ",
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier
                        .padding(bottom = 12.dp)
            )
        }
    }
    
}