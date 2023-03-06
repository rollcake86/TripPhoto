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



fun contentTypeId(id : String) : String {
//    관광타입(75:레포츠, 76:관광지, 77:교통, 78:문화시설, 79:쇼핑, 80:숙박, 82:음식점, 85:축제공연행사) ID
    when(id) {
        "75" -> return "Sport"
        "76" -> return "Place"
        "77" -> return "Traffic"
        "78" -> return "Hot Place"
        "79" -> return "Shopping"
        "80" -> return "Hotel"
        "82" -> return "Food"
        "85" -> return "Festival"
    }
    return "Sport"
}