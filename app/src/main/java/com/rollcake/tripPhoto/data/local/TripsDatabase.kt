package com.rollcake.tripPhoto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rollcake.tripPhoto.network.TripProperty

/**
 * The Room Database that contains the reminders table.
 */
@Database(entities = [TripProperty::class], version = 1, exportSchema = false)
abstract class TripsDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
}