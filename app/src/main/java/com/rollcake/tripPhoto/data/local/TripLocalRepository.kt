package com.rollcake.tripPhoto.data.local

import com.rollcake.tripPhoto.data.TripDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.rollcake.tripPhoto.data.dto.Result
import com.rollcake.tripPhoto.network.TripProperty

/**
 * Concrete implementation of a data source as a db.
 *
 * The repository is implemented so that you can focus on only testing it.
 *
 * @param tripDao the dao that does the Room db operations
 * @param ioDispatcher a coroutine dispatcher to offload the blocking IO tasks
 */
class TripLocalRepository(
    private val tripDao: TripDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TripDataSource {

    /**
     * Get the reminders list from the local db
     * @return Result the holds a Success with all the reminders or an Error object with the error message
     */
    override suspend fun getTrips(): Result<List<TripProperty>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(tripDao.getTrips())
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun saveTrip(trip: TripProperty) =
        withContext(ioDispatcher) {
            tripDao.saveTrip(trip)
        }

    /**
     * Get a reminder by its id
     * @param id to be used to get the reminder
     * @return Result the holds a Success object with the Reminder or an Error object with the error message
     */
    override suspend fun getTrip(id: String): Result<TripProperty> = withContext(ioDispatcher) {
        try {
            val reminder = tripDao.getTripById(id)
            if (reminder != null) {
                return@withContext Result.Success(listOf(reminder))
            } else {
                return@withContext Result.Error("Reminder not found!")
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e.localizedMessage)
        }
    }

    /**
     * Deletes all the reminders in the db
     */
    override suspend fun deleteAllTrips() {
        withContext(ioDispatcher) {
            tripDao.deleteAllTrips()
        }
    }
}
