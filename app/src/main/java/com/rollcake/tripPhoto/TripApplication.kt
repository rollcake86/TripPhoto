package com.rollcake.tripPhoto

import android.app.Application
import com.google.firebase.FirebaseApp
import com.rollcake.tripPhoto.authentication.FirebaseUserLiveData
import com.rollcake.tripPhoto.data.local.LocalDB
import timber.log.Timber

class TripApplication : Application() {

    lateinit var firebaseUserLiveData : FirebaseUserLiveData

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        LocalDB.createRemindersDao(this)
        Timber.asTree()
        firebaseUserLiveData = FirebaseUserLiveData()
    }
}