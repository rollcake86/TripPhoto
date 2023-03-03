package com.rollcake.tripPhoto.ui.setting

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.data.TripDataSource
import kotlinx.coroutines.launch

class SettingViewModel(val app: Application, val dataSource: TripDataSource) :
    BaseViewModel(app) {


    fun deleteTripData() {
        showLoading.value = true
        viewModelScope.launch {
            dataSource.deleteAllReminders()
            showLoading.value = false
            showToast.value = app.getString(R.string.delete_all_data)
        }
    }

}