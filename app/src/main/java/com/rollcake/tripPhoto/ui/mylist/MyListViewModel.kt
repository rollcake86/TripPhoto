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
    val tripsList = MutableLiveData<List<TripProperty>>()

    fun loadTrips() {
        showLoading.postValue(true)
        viewModelScope.launch {
            val result = dataSource.getTrips()
            showLoading.postValue(false)
            when (result) {
                is com.rollcake.tripPhoto.data.dto.Result.Success<*> -> {
                    tripsList.value = result.data!!
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