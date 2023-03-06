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
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentDetailBinding
import com.rollcake.tripPhoto.databinding.FragmentSettingBinding
import com.rollcake.tripPhoto.network.contentTypeId
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailFragment : BaseFragment() {

    override val _viewModel: DetailViewModel by inject()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val trip = DetailFragmentArgs.fromBundle(requireArguments()).selectedTrip
        Glide.with(this.requireContext()).load(trip.firstimage).into(view.findViewById<ImageView>(R.id.imageView2))
        binding.trip = trip
        binding.lifecycleOwner = this

        view.findViewById<ImageView>(R.id.help_button).setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:${trip.mapy},${trip.mapx}?q=${trip.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        view.findViewById<TextView>(R.id.trip_title_textview).text = trip.title
        view.findViewById<TextView>(R.id.trip_addr_textview).text = trip.addr1
        view.findViewById<TextView>(R.id.trip_dist_textview).text = "${trip.dist.toDouble().toInt()} m"
        view.findViewById<TextView>(R.id.trip_tel_textview).text = trip.tel
        view.findViewById<TextView>(R.id.trip_type_textview).text = contentTypeId(trip.contenttypeid)

        view.findViewById<ImageView>(R.id.save_btn).setOnClickListener {


        }

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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