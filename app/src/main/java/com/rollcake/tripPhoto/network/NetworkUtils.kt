package com.rollcake.tripPhoto.network

import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject

fun parseJsonResult(jsonResult: JSONObject): ArrayList<TripProperty> {
    val tripList = ArrayList<TripProperty>()

    val totalCount = jsonResult.getJSONObject("response").getJSONObject("body").getInt("totalCount")
    if (totalCount != 0){
        val responseJson = jsonResult.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item")
        for (i in 0 until responseJson.length()) {
            val tripJson = responseJson.getJSONObject(i)
            Log.e("TAGTAG" , tripJson.toString())
            val data = Gson().fromJson(tripJson.toString() , TripProperty::class.java)
            tripList.add(data)
        }
    }

    return tripList
}