package com.javapda

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ParkingSpotTest {
    @Test
    fun `is spot occupied`() {
        val parkingSpot3 =ParkingSpot(3)
        assertFalse(parkingSpot3.isOccupied())
        assertTrue(parkingSpot3.number == 3)
        parkingSpot3.vehicle=Vehicle("White", "34-AZ")
        assertTrue(parkingSpot3.isOccupied())
        parkingSpot3.clear()
        assertFalse(parkingSpot3.isOccupied())
    }
}