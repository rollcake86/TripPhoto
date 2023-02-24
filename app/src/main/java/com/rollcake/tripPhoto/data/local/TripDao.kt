package com.rollcake.tripPhoto.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rollcake.tripPhoto.data.dto.TripDTO

/**
 * Data Access Object for the reminders table.
 */
@Dao
interface TripDao {

    @Query(/* value = */ "SELECT * FROM trips")
    suspend fun getTrips(): List<TripDTO>?

    @Query(/* value = */ "SELECT * FROM trips where entry_id = :tripId")
    suspend fun getTripById(tripId: String): TripDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTrip(trip: TripDTO)

    @Query(/* value = */ "DELETE FROM trips")
    suspend fun deleteAllTrips()

}