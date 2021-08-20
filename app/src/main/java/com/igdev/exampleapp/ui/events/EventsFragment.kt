package com.igdev.exampleapp.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.igdev.exampleapp.R
import com.igdev.exampleapp.databinding.FragmentEventsBinding
import com.igdev.exampleapp.models.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {
    //region Fields

    private val viewModel: EventsViewModel by viewModels()

    private lateinit var binding: FragmentEventsBinding

    //endregion

    //region Overrides

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setObservers()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onEventSelected("")
    }

    //endregion

    //region Private Methods

    private fun setObservers() {
        viewModel.events.observe(viewLifecycleOwner, this::refreshEventList)
        viewModel.getEventSelected().observe(viewLifecycleOwner, { eventId ->
            if (eventId.isEmpty()) return@observe

            val toDetails = EventsFragmentDirections.actionEventsToDetails(eventId)
            findNavController().navigate(toDetails)
        })
    }

    private fun refreshEventList(events: List<Event>) {
        binding.rvEvents.layoutManager = LinearLayoutManager(context)
        binding.rvEvents.adapter = EventsAdapter(events, viewModel::onEventSelected)
    }

    //endregion
}