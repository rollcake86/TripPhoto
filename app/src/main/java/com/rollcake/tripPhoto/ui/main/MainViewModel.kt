package com.rollcake.tripPhoto.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.network.TripApi
import com.rollcake.tripPhoto.network.TripProperty
import com.rollcake.tripPhoto.network.parseJsonResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(val app: Application) :
    BaseViewModel(app) {

    private val _tripData = MutableLiveData<ArrayList<TripProperty>>()

    val tripData : LiveData<ArrayList<TripProperty>>
        get() = _tripData


    fun getTripProperties(lat: Double, loc: Double) {
        viewModelScope.launch {
            val trip = TripApi.retrofitService.getProperties(
                mapX =
                loc, mapY = lat
            )
            val result = coroutineScope {
                val deferred = async {
                    parseJsonResult(JSONObject(trip.await()))
                }
                deferred.await()

            }
            _tripData.value = result
        }
    }
}