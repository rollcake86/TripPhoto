package com.rollcake.tripPhoto.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.data.TripDataSource
import com.rollcake.tripPhoto.network.TripProperty
import kotlinx.coroutines.launch

class DetailViewModel(val app: Application, val dataSource: TripDataSource) :
    BaseViewModel(app) {

    private val _tripData = MutableLiveData<TripProperty>()

    val tripData: LiveData<TripProperty>
        get() = _tripData

    fun saveReminder(trip: TripProperty) {
        showLoading.value = true
        viewModelScope.launch {
            dataSource.saveTrip(
                trip
            )
            showLoading.value = false
            showToast.value = app.getString(R.string.trip_save)
            navigationCommand.value = NavigationCommand.Back
        }
    }

}