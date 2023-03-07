package com.rollcake.tripPhoto.ui.mylist

import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseRecyclerViewAdapter


//Use data binding to show the reminder on the item
class TripsListAdapter(callBack: (selectedTrp: TripsDataItem) -> Unit) :
    BaseRecyclerViewAdapter<TripsDataItem>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.it_trip
}