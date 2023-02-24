package com.rollcake.tripPhoto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rollcake.tripPhoto.data.dto.TripDTO

/**
 * The Room Database that contains the reminders table.
 */
@Database(entities = [TripDTO::class], version = 1, exportSchema = false)
abstract class TripsDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
}