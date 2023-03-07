package com.rollcake.tripPhoto.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentDetailBinding
import com.rollcake.tripPhoto.network.TripProperty
import com.rollcake.tripPhoto.network.contentTypeId
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment() {

    override val _viewModel: DetailViewModel by inject()
    private lateinit var binding: FragmentDetailBinding
    lateinit var trip : TripProperty
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        trip = DetailFragmentArgs.fromBundle(requireArguments()).selectedTrip

        binding.trip = trip
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this.requireContext()).load(trip.firstimage).into(view.findViewById(R.id.imageView2))
        view.findViewById<ImageView>(R.id.help_button).setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:${trip.mapy},${trip.mapx}?q=${trip.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        view.findViewById<TextView>(R.id.trip_dist_textview).text = "${trip.dist.toDouble().toInt()} m"
        view.findViewById<TextView>(R.id.trip_type_textview).text = contentTypeId(trip.contenttypeid)

        view.findViewById<Button>(R.id.save_btn).setOnClickListener {
            if(_viewModel.saveCheck.value!!){
                _viewModel.removeTrip(trip)
            }else{
                _viewModel.saveTrip(trip)
            }
        }
        _viewModel.saveCheck(trip)
        _viewModel.saveCheck.observe(viewLifecycleOwner){
            if(it) {
                view.findViewById<Button>(R.id.save_btn).setText(getString(R.string.trip_remove))
            } else {
                view.findViewById<Button>(R.id.save_btn).setText(getString(R.string.trip_save))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                _viewModel.navigationCommand.value = NavigationCommand.Back
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}