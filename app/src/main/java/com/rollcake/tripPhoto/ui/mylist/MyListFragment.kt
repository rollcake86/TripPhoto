package com.rollcake.tripPhoto.ui.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.base.BaseViewModel
import com.rollcake.tripPhoto.base.NavigationCommand
import com.rollcake.tripPhoto.databinding.FragmentTripsBinding
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import com.rollcake.utils.setTitle
import com.rollcake.utils.setup
import org.koin.android.ext.android.inject

class MyListFragment : BaseFragment(){

    override val _viewModel : MyListViewModel by inject()
    private lateinit var binding : FragmentTripsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_trips , container , false)
        binding.viewModel = _viewModel


        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.history))

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        setupRecyclerView()
    }
    override fun onResume() {
        super.onResume()
        //load the reminders list on the ui
        _viewModel.loadTrips()
    }

    private fun setupRecyclerView() {
        val adapter = TripsListAdapter {
            _viewModel.navigationCommand.value = NavigationCommand.To(MyListFragmentDirections.actionMyListFragmentToDetailFragment(it))
        }
        binding.reminderssRecyclerView.setup(adapter)
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