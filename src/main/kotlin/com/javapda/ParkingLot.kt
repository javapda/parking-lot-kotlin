package com.javapda

class ParkingLot {
    init {
        var color: String = readln()
        println("$color car has parked.")
        color = readln()
        println("$color car left the parking lot.")
        color = readln()
        println("$color car just parked here.")
    }
}

fun main() {
    ParkingLot()
}