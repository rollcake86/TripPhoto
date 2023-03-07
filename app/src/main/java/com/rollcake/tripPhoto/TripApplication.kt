package com.rollcake.tripPhoto

import android.app.Application
import com.google.firebase.FirebaseApp
import com.rollcake.tripPhoto.authentication.FirebaseUserLiveData
import com.rollcake.tripPhoto.data.TripDataSource
import com.rollcake.tripPhoto.data.local.LocalDB
import com.rollcake.tripPhoto.data.local.TripDao
import com.rollcake.tripPhoto.data.local.TripLocalRepository
import com.rollcake.tripPhoto.ui.detail.DetailViewModel
import com.rollcake.tripPhoto.ui.main.MainViewModel
import com.rollcake.tripPhoto.ui.mylist.MyListViewModel
import com.rollcake.tripPhoto.ui.setting.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class TripApplication : Application() {

    lateinit var firebaseUserLiveData : FirebaseUserLiveData
    lateinit var tripDao: TripDao
    lateinit var tripDataSource: TripDataSource

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        tripDao = LocalDB.createTripsDao(this)
        tripDataSource = TripLocalRepository(tripDao)
        Timber.asTree()
        firebaseUserLiveData = FirebaseUserLiveData()

        val myModule = module {
            viewModel {
                MainViewModel(
                    get()
                )
            }
            single {
                SettingViewModel(
                    get()
                )
            }
            single {
                DetailViewModel(
                    get(),
                    tripDataSource
                )
            }
            single {
                MyListViewModel(
                    get(),
                    tripDataSource
                )
            }

        }

        startKoin {
            androidContext(this@TripApplication)
            modules(listOf(myModule))
        }

    }
}