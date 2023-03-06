package com.rollcake.tripPhoto.ui.setting

import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.TripApplication
import com.rollcake.tripPhoto.base.BaseViewModel
import kotlinx.coroutines.launch

class SettingViewModel(val app: TripApplication) :
    BaseViewModel(app) {

    fun deleteTripData() {
        showLoading.value = true
        viewModelScope.launch {
//            dataSource.deleteAllReminders()
            showLoading.value = false
            showToast.value = app.getString(R.string.delete_all_data)
        }
    }

}