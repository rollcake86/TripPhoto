package com.rollcake.tripPhoto.ui.main

import android.app.Application
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.data.TripDataSource

class MainViewModel(val app: Application, val dataSource: TripDataSource) :
    BaseViewModel(app) {

}