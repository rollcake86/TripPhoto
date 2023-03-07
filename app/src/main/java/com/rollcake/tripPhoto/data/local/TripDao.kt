package com.rollcake.tripPhoto.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rollcake.tripPhoto.network.TripProperty

/**
 * Data Access Object for the reminders table.
 */
@Dao
interface TripDao {

    @Query(/* value = */ "SELECT * FROM trips")
    suspend fun getTrips(): List<TripProperty>?

    @Query(/* value = */ "SELECT * FROM trips where contentid = :tripId")
    suspend fun getTripById(tripId: String): TripProperty?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTrip(trip: TripProperty)

    @Query(/* value = */ "DELETE FROM trips")
    suspend fun deleteAllTrips()

}