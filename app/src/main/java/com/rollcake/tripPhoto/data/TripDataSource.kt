package com.rollcake.tripPhoto.data

import com.rollcake.tripPhoto.data.dto.Result
import com.rollcake.tripPhoto.data.dto.TripDTO

/**
 * Main entry point for accessing reminders data.
 */
interface TripDataSource {
    suspend fun getReminders(): Result<List<TripDTO>>
    suspend fun saveReminder(trip: TripDTO)
    suspend fun getReminder(id: String): com.rollcake.tripPhoto.data.dto.Result<TripDTO>
    suspend fun deleteAllReminders()
}