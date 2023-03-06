package com.rollcake.tripPhoto.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentDetailBinding
import com.rollcake.tripPhoto.databinding.FragmentSettingBinding
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject

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


        binding.trip = trip
        binding.lifecycleOwner = this

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