package com.rollcake.tripPhoto.data

import com.rollcake.tripPhoto.data.dto.Result
import com.rollcake.tripPhoto.network.TripProperty

/**
 * Main entry point for accessing reminders data.
 */
interface TripDataSource {
    suspend fun getTrips(): Result<List<TripProperty>>
    suspend fun saveTrip(trip: TripProperty)
    suspend fun getTrip(id: String): Result<TripProperty>
    suspend fun deleteAllTrips()
}