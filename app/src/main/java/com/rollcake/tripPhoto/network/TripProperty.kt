package com.rollcake.tripPhoto.network

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "trips")
@Parcelize
data class TripProperty(
    @PrimaryKey
    val contentid : String,
    val addr1 : String,
    val addr2 : String,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
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

