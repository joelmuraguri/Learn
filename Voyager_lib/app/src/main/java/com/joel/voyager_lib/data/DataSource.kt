package com.joel.voyager_lib.data

data class Bookings(
    val id : Int,
    val name : String,
    val title : String,
    val time : Long
)

object DataStore{

    val bookings = listOf(
        Bookings(
            id = 1,
            name = "Joel Muraguri",
            title = "Android Dev",
            time = System.currentTimeMillis()
        ),
        Bookings(
            id = 2,
            name = "John Doe",
            title = "Golang Dev",
            time = System.currentTimeMillis()
        ),
        Bookings(
            id = 3,
            name = "John Wick",
            title = "Product Manager",
            time = System.currentTimeMillis()
        ),
        Bookings(
            id = 4,
            name = "Mark Grayson",
            title = "HR",
            time = System.currentTimeMillis()
        ),
    )

}