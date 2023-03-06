package com.rollcake.tripPhoto.ui.setting

import android.app.Application
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.authentication.AuthenticationActivity
import com.rollcake.tripPhoto.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingViewModel(val app: Application ) :
    BaseViewModel(app) {

    fun deleteTripData() {
        showLoading.value = true
        viewModelScope.launch {
//            dataSource.deleteAllReminders()
            showLoading.value = false
            showToast.value = app.getString(R.string.delete_all_data)
        }
    }

    fun logout(){
        Timber.i("logout!!!")
        showToast.value =  app.getString(R.string.logout_success)
        AuthUI.getInstance().signOut(app) .addOnCompleteListener {
            app.startActivity(
                Intent(app , AuthenticationActivity::class.java).addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}