package com.javapda

import java.util.*

/**
 * Vehicle
 *
 * @property registrationNumber
 * @property color
 * @constructor Create empty Vehicle
 */
data class Vehicle(val registrationNumber: String, val color: String)

/**
 * Parking spot
 *
 * @property number
 * @property vehicle
 * @constructor Create empty Parking spot
 */
data class ParkingSpot(val number: Int, var vehicle: Vehicle? = null) {
    fun clear() {
        vehicle = null
    }

    fun isOccupied() = vehicle != null
    fun isAvailable() = !isOccupied()

}

/**
 * Parking lot
 *
 * @property numberOfSpots
 * @constructor Create empty Parking lot
 */
class ParkingLot(private var numberOfSpots: Int = 2) {
    /**
     *
     */
    constructor(numberOfSpots: Int, vehicles: List<Vehicle>) : this(numberOfSpots) {
        if (vehicles.size > numberOfSpots) {
            throw IllegalArgumentException("${vehicles.size} cannot fit in $numberOfSpots parking spots")
        }
        vehicles.forEach(::parkVehicle)
    }

    private var parkingSpots = mutableListOf<ParkingSpot>()

    init {
        if (numberOfSpots > 0) {
            createParkingSpots(numberOfSpots)
        }
    }

    fun userInput(command: String, verbose: Boolean = false): Boolean {
        if (command.isBlank()) return true
        val list = command.split(" ")
        if (verbose)
            println(
                """
            command: $command
            list:    $list
        """.trimIndent()
            )
        when (list.first()) {
            "create" -> createParkingSpots(list[1].toInt())
            "leave" -> leave(list[1].toInt())
            "park" -> parkVehicle(Vehicle(list[1], list[2]), true)
            "exit" -> return false
            else -> throw IllegalArgumentException("unknown command '$command'")
        }
        return true
    }

    private fun parkingSpot(parkingSpotNumber: Int): ParkingSpot {
        return parkingSpots[parkingSpotNumber - 1]
    }

    private fun leave(parkingSpotNumber: Int) {
        if (parkingSpotNumber > parkingSpots.size) {
            println("ERROR: there are only ${parkingSpots.size} spots, so spot $parkingSpotNumber does NOT exist!")
            return
        } else if (parkingSpotNumber < 0) {
            println("ERROR: you must choose between 1 and ${parkingSpots.size} spots, spot $parkingSpotNumber is invalid!")
            return

        }
        if (parkingSpot(parkingSpotNumber).isOccupied()) {
            parkingSpot(parkingSpotNumber).vehicle = null
            println("Spot ${parkingSpot(parkingSpotNumber).number} is free.")
        } else {
            println("There is no car in spot ${parkingSpot(parkingSpotNumber).number}.")
        }

    }

    private fun parkVehicle(vehicle: Vehicle, verbose: Boolean = false) {
        if (isFull()) {
            println("Sorry, lot is full!")
            return
        }
        val firstAvailableParkingSpot = parkingSpots.first(ParkingSpot::isAvailable)
        firstAvailableParkingSpot.vehicle = vehicle
        if (verbose)
            println("${vehicle.color} car parked in spot ${firstAvailableParkingSpot.number}.")
    }

    private fun isFull(): Boolean = !parkingSpots.any(ParkingSpot::isAvailable)

    private fun createParkingSpots(numberOfSpotsToCreate: Int) {
        parkingSpots = MutableList(numberOfSpotsToCreate) { i -> ParkingSpot(i + 1) }
    }

}

fun main() {
    val parkingLot = ParkingLot(numberOfSpots = 2, listOf(Vehicle("234-AZ", "Green")))
    val scanner = Scanner(System.`in`)
    // NOTE: it's messed up that Hyperskill accepted this as correct - oh, well
    //while (scanner.hasNext() && parkingLot.userInput(scanner.nextLine()));
    parkingLot.userInput(readln())
    // parkingLot.userInput(scanner.nextLine())
    //parkingLot.userInput(scanner.nextLine())

}