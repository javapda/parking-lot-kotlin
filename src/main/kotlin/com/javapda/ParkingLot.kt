package com.javapda

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
class ParkingLot(private var numberOfSpots: Int = 0) {
    /**
     *
     */
    constructor(numberOfSpots: Int, vehicles: List<Vehicle>) : this(numberOfSpots) {
        if (vehicles.size > numberOfSpots) {
            throw IllegalArgumentException("${vehicles.size} cannot fit in $numberOfSpots parking spots")
        }
        vehicles.forEach(::parkVehicle)
    }

    private var parkingLotCreated: Boolean = false
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
        if (!parkingLotCreated && !command.startsWith("create ")) {
            println("Sorry, a parking lot has not been created.")
            return true
        }
        when (list.first()) {
            "create" -> createParkingSpots(list[1].toInt())
            "leave" -> leave(list[1].toInt())
            "park" -> parkVehicle(Vehicle(list[1], list[2]), true)
            "status" -> printParkingLotStatus()
            "reg_by_color" -> printRegistrationNumbersForColor(list[1])
            "spot_by_color" -> printSpotForVehicleColor(list[1])
            "spot_by_reg" -> printSpotForVehicleRegistrationNumber(list[1])
            "exit" -> return false
            else -> throw IllegalArgumentException("unknown command '$command'")
        }
        return true
    }

    private fun printSpotForVehicleColor(color: String) {
        val found = parkingSpots.filter { spot ->
            spot.isOccupied() && spot.vehicle?.color.equals(
                other = color,
                ignoreCase = true
            )
        }
        println(if (found.isEmpty()) "No cars with color $color were found." else found.map { spot -> spot.number }
            .joinToString(", "))
    }

    private fun printSpotForVehicleRegistrationNumber(vehicleRegistrationNumber: String) {
        val found =
            parkingSpots.filter { spot -> spot.isOccupied() && spot.vehicle?.registrationNumber == vehicleRegistrationNumber }
        println(if (found.isEmpty()) "No cars with registration number $vehicleRegistrationNumber were found." else found.first().number)
    }

    private fun printRegistrationNumbersForColor(color: String) {
        val found = parkingSpots.filter(ParkingSpot::isOccupied)
            .filter { spot -> spot.vehicle?.color.equals(color, ignoreCase = true) }
            .map { parkingSpot -> parkingSpot.vehicle?.registrationNumber }
            .joinToString(", ")
        println(if (found.isEmpty()) "No cars with color $color were found." else found)
    }


    private fun printParkingLotStatus() {
        if (isEmpty()) {
            println("Parking lot is empty.")
        } else {
            fun printParkingSpot(parkingSpot: ParkingSpot) =
                println("${parkingSpot.number} ${parkingSpot.vehicle?.registrationNumber} ${parkingSpot.vehicle?.color}")
            parkingSpots.filter(ParkingSpot::isOccupied).forEach(::printParkingSpot)
        }
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
            println("Sorry, the parking lot is full.")
            return
        }
        val firstAvailableParkingSpot = parkingSpots.first(ParkingSpot::isAvailable)
        firstAvailableParkingSpot.vehicle = vehicle
        if (verbose)
            println("${vehicle.color} car parked in spot ${firstAvailableParkingSpot.number}.")
    }

    private fun isFull(): Boolean = !parkingSpots.any(ParkingSpot::isAvailable)
    private fun isEmpty(): Boolean = !parkingSpots.any(ParkingSpot::isOccupied)

    private fun createParkingSpots(numberOfSpotsToCreate: Int) {
        parkingLotCreated = false
        parkingSpots = MutableList(numberOfSpotsToCreate) { i -> ParkingSpot(i + 1) }
        parkingLotCreated = true
        println("Created a parking lot with $numberOfSpotsToCreate spots.")

    }

}

fun main() {
    val parkingLot = ParkingLot()
    while (true) {
        val userInput = readln().trim()
        val input = userInput.split(" ")
        when (input[0]) {
            "exit" -> break
            else -> parkingLot.userInput(userInput)
        }
    }

}