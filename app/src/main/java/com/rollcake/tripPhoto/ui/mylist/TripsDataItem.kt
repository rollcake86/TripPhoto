package com.rollcake.tripPhoto.ui.mylist

import java.io.Serializable
import java.util.*

/**
 * data class acts as a data mapper between the DB and the UI
 */
data class TripsDataItem(
    var contentid: String?,
    var title: String?,
    var addr1: String?,
    var tel: String?,
    var contenttypeid: String?,
    var firstimage: String?,
    var latitude: Double?,
    var longitude: Double?,
) : Serializable