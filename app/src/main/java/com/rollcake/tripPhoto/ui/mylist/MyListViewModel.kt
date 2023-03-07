package com.rollcake.tripPhoto.ui.mylist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.data.TripDataSource
import com.rollcake.tripPhoto.network.TripProperty
import kotlinx.coroutines.launch

class MyListViewModel(val app: Application, private val dataSource: TripDataSource) :
    BaseViewModel(app) {
    val tripsList = MutableLiveData<List<TripsDataItem>>()

    fun loadTrips() {
        showLoading.postValue(true)
        viewModelScope.launch {
            val result = dataSource.getTrips()
            showLoading.postValue(false)
            when (result) {
                is com.rollcake.tripPhoto.data.dto.Result.Success<*> -> {
                    val dataList = ArrayList<TripsDataItem>()
                    dataList.addAll((result.data as List<TripProperty>).map { trip ->
                        TripsDataItem(
                            contentid = trip.contentid,
                            trip.title,
                            trip.addr1,
                            trip.tel,
                            trip.contenttypeid,
                            trip.firstimage,
                            trip.mapy.toDouble(),
                            trip.mapx.toDouble()
                        )
                    })
                    tripsList.value = dataList
                }
                is com.rollcake.tripPhoto.data.dto.Result.Error ->
                    showSnackBar.value = result.message
            }

            invalidateShowNoData()
        }
    }

    /**
     * Inform the user that there's not any data if the remindersList is empty
     */
    private fun invalidateShowNoData() {
        showNoData.value = tripsList.value == null || tripsList.value!!.isEmpty()
    }


}