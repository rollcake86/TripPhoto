package com.rollcake.tripPhoto.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.authentication.AuthenticationActivity
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentSettingBinding
import com.rollcake.tripPhoto.ui.main.MainFragmentDirections
import com.rollcake.tripPhoto.ui.main.MainViewModel
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject


class SettingFragment : BaseFragment(){

    override val _viewModel: MainViewModel by inject()
    private lateinit var binding: FragmentSettingBinding
            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

//                binding =
//                    DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
//
//                binding.viewModel = _viewModel
//                binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)
                return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun logout(){
        _viewModel.showToast.value = getString(R.string.logout_success)
        AuthUI.getInstance().signOut(this.requireContext()) .addOnCompleteListener {
            requireActivity().startActivity(Intent(this.context , AuthenticationActivity::class.java).addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY))
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