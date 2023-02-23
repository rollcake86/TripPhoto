package com.rollcake.tripPhoto

import android.app.Application
import com.google.firebase.FirebaseApp

class TripApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}