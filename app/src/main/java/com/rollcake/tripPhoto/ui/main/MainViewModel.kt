package com.rollcake.tripPhoto.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.data.TripDataSource
import com.rollcake.tripPhoto.network.TripAPIProperty
import com.rollcake.tripPhoto.network.TripApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel(val app: Application) :
    BaseViewModel(app) {



    fun getTripProperties() {
        viewModelScope.launch {
            try {
                val trip = TripApi.retrofitService.getProperties(
                    "hm3Ng%2Bp0hajUH1lyqqB1JTmURPuIidiOj%2BoR1I49TQDEJPB9eY9CrArmUXrlx1PQ1DqvA%2B%2FqNSJWJhFa73mamw%3D%3D",
                    "20",
                    "1",
                    "ETC",
                    "Korea Trip",
                    "json", "Y", "C", 126.98611, 37.568477, 1000
                ).enqueue(object : retrofit2.Callback<TripAPIProperty> {
                    override fun onResponse(
                        call: Call<TripAPIProperty>,
                        response: Response<TripAPIProperty>
                    ) {
                        Log.e("TAGTAG2" , response.body().toString())
                    }

                    override fun onFailure(call: Call<TripAPIProperty>, t: Throwable) {
                        Log.e("TAGTAG2" , t.message.toString())
                    }

                })

            } catch (e: java.lang.Exception) {
                Log.e("TAGTAG" , e.localizedMessage)
            }
        }
    }
}