package com.rollcake.tripPhoto.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentMainBinding
import com.rollcake.tripPhoto.network.TripApi
import com.rollcake.tripPhoto.network.contentTypeId
import com.rollcake.tripPhoto.network.contentTypeIdIcon
import com.rollcake.tripPhoto.ui.detail.DetailFragmentArgs
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainFragment() : BaseFragment()  , OnMapReadyCallback{

    override val _viewModel: MainViewModel by inject()
    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var map: GoogleMap
    private lateinit var selectedMarker: Marker
    private lateinit var binding : FragmentMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    enableMyLocation()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    enableMyLocation()
                }

                else -> {
                    Timber.i("Permission: ", "Denied")
                    Toast.makeText(
                        context,
                        "Location permission was not granted.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)


        binding.viewModel = _viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        _viewModel.tripData.observe(viewLifecycleOwner) {
            for (item in it) {
                map.addMarker(
                    MarkerOptions()
                        .position(LatLng(item.mapy.toDouble() , item.mapx.toDouble()))
                        .title(item.title)
                        .snippet(item.addr1)
                        .icon(tripIcon(item.contenttypeid))
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            val target = map.cameraPosition.target
            _viewModel.getTripProperties(target.latitude , target.longitude)
        }

        view.findViewById<FloatingActionButton>(R.id.detail_btn).setOnClickListener {
            for(i in _viewModel.tripData.value!!){
                if(i.title == selectedMarker.title){
                    _viewModel.navigationCommand.value = NavigationCommand.To(MainFragmentDirections.actionMainFragmentToDetailFragment(i))
                }
            }
        }

        view.findViewById<FloatingActionButton>(R.id.mylist_btn).setOnClickListener {
            _viewModel.navigationCommand.value = NavigationCommand.To(MainFragmentDirections.actionMainFragmentToMyListFragment())
        }
    }

    private fun tripIcon(contenttypeid: String): BitmapDescriptor? {
        return BitmapDescriptorFactory.fromResource(contentTypeIdIcon(contenttypeid))
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setMapStyle(map)
        setPoiClick(map)
        enableMyLocation()
    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )
            if (!success) {
                Timber.e("Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Timber.e("Can't find style. Error: ", e)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
            Toast.makeText(context, getString(R.string.location_permission_success), Toast.LENGTH_SHORT).show()
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.565117,126.9927), 15f))
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnMarkerClickListener {
            this.view?.findViewById<FloatingActionButton>(R.id.detail_btn)?.visibility = View.VISIBLE
            selectedMarker = it
            false }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                _viewModel.navigationCommand.value =
                    NavigationCommand.To(MainFragmentDirections.actionMainFragmentToSettingFragment())
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
//        display logout as menu item
        inflater.inflate(R.menu.main_menu, menu)
    }
}