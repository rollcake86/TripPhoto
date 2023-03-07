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

    private val _saveCheck = MutableLiveData<Boolean>()
    val saveCheck : LiveData<Boolean>
    get() = _saveCheck

    private val _tripData = MutableLiveData<TripProperty>()

    val tripData: LiveData<TripProperty>
        get() = _tripData


    fun saveCheck(trip : TripProperty){
        viewModelScope.launch {
            val result = dataSource.getTrip(trip.contentid)
            when(result){
                is com.rollcake.tripPhoto.data.dto.Result.Success -> {
                    _saveCheck.value = true
                }
                is com.rollcake.tripPhoto.data.dto.Result.Error -> {
                    _saveCheck.value = false
                }
            }
        }
    }


    fun saveTrip(trip: TripProperty) {
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

    fun removeTrip(trip: TripProperty) {
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