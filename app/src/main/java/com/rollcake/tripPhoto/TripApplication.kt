package com.rollcake.tripPhoto

import android.app.Application
import com.google.firebase.FirebaseApp
import com.rollcake.tripPhoto.authentication.FirebaseUserLiveData
import com.rollcake.tripPhoto.data.TripDataSource
import com.rollcake.tripPhoto.data.local.LocalDB
import com.rollcake.tripPhoto.data.local.TripLocalRepository
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

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        LocalDB.createRemindersDao(this)
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
            //Declare singleton definitions to be later injected using by inject()
            single {
                MyListViewModel(
                    get(),
                    get() as TripDataSource
                )
            }
            single { TripLocalRepository(get()) }
            single { LocalDB.createRemindersDao(this@TripApplication) }
        }

        startKoin {
            androidContext(this@TripApplication)
            modules(listOf(myModule))
        }

    }
}