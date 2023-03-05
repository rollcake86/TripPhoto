package com.rollcake.tripPhoto.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TripProperty(
    val addr1 : String,
    val addr2 : String,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
    val contentid : String,
    val contenttypeid : String,
    val createdtime : String,
    val dist : String,
    val firstimage : String,
    val firstimage2 : String,
    val cpyrhtDivCd : String,
    val mapx : String,
    val mapy : String,
    val mlevel : String,
    val modifiedtime : String,
    val sigungucode : String,
    val tel : String,
    val title : String,
) : Parcelable

@Parcelize
data class TripAPIProperty(
    val response : header,
    val body : body
) : Parcelable

@Parcelize
data class header(
    val resultCode : String,
    val resultMsg : String,
) : Parcelable

@Parcelize
data class body(
    val items: item ,
    val numOfRows : Int,
    val pageNo : Int,
    val totalCount : Int,
) : Parcelable

@Parcelize
data class item(
    val item : List<TripProperty>
) : Parcelable