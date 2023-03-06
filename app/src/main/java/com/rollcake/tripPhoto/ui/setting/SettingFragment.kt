package com.rollcake.tripPhoto.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.authentication.AuthenticationActivity
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentSettingBinding
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject


class SettingFragment : BaseFragment(){

    override val _viewModel: SettingViewModel by inject()
    private lateinit var binding: FragmentSettingBinding
            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
                val view = inflater.inflate(R.layout.fragment_setting, container, false)

                binding =
                    DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

                binding.viewModel = _viewModel
                binding.lifecycleOwner = this

                view.findViewById<Button>(R.id.logout_btn).setOnClickListener {
                    _viewModel.logout()
                }

                view.findViewById<Button>(R.id.delete_all_data_btn).setOnClickListener {
                    _viewModel.deleteTripData()
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