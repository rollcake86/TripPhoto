package com.rollcake.tripPhoto.data.local

import android.content.Context
import androidx.room.Room

/**
 * Singleton class that is used to create a reminder db
 */
object LocalDB {

    fun createRemindersDao(context: Context): TripDao {
        return Room.databaseBuilder(
            context.applicationContext,
            TripsDatabase::class.java, "trips.db"
        ).build().tripDao()
    }

}