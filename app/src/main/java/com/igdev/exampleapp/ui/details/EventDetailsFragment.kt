package com.igdev.exampleapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.igdev.exampleapp.R
import com.igdev.exampleapp.databinding.FragmentEventDetailsBinding
import com.igdev.exampleapp.extensions.hide
import com.igdev.exampleapp.extensions.show
import com.igdev.exampleapp.models.Person
import com.igdev.exampleapp.ui.checkin.CheckinDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class EventDetailsFragment: Fragment() {
    //region Fields

    private val viewModel: EventDetailsViewModel by viewModels()
    private val args: EventDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentEventDetailsBinding

    //endregion

    //region Overrides

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_details, container, false)

        binding.root.hide()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setObservers()
        setClickListeners()
        getEventDetails()

        return binding.root
    }

    //endregion

    //region Private Methods

    private fun setObservers() {
        viewModel.getEvent().observe(viewLifecycleOwner, { event ->
            binding.root.show()
            buildPeopleList(event.people)
        })
    }

    private fun setClickListeners() {
        binding.btCheckin.setOnClickListener {
            showCheckinDialog()
        }
    }

    private fun getEventDetails() {
        viewModel.getEventDetails(args.eventId)
    }

    private fun buildPeopleList(people: List<Person>) {
        binding.rvEvents.layoutManager = LinearLayoutManager(context)
        binding.rvEvents.adapter = PeopleAdapter(people)
    }

    private fun showCheckinDialog() {
        val fragmentManager = parentFragmentManager
        val dialogFragment = CheckinDialogFragment(args.eventId)

        dialogFragment.show(fragmentManager, "checkinDialogFragment")
    }

    //endregion
}