package com.rollcake.tripPhoto.ui.mylist

import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseRecyclerViewAdapter
import com.rollcake.tripPhoto.network.TripProperty


//Use data binding to show the reminder on the item
class TripsListAdapter(callBack: (selectedTrp: TripProperty) -> Unit) :
    BaseRecyclerViewAdapter<TripProperty>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.it_trip
}