package com.joel.voyager_lib.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joel.voyager_lib.data.Bookings
import com.joel.voyager_lib.presentation.BookingDetails
import com.joel.voyager_lib.presentation.Bookings
import com.joel.voyager_lib.presentation.Home


object HomeScreen : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Home(
          onNav = {
              navigator.push(BookingsScreen)
          }
        )
    }

}
object BookingsScreen : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Bookings(
            toHome = {
                navigator.push(HomeScreen)
            },
            onItemClick = { bookings ->
                navigator.push(BookingsDetailScreens(bookings))
            }
        )
    }
}

// handling navigation with arguments
data class BookingsDetailScreens(
    val bookings: Bookings,
) : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        BookingDetails(
            bookings = bookings,
            onNavToBookings = {
                navigator.push(BookingsScreen)
            }
        )

    }

}