package com.example.ticketmasterdemochallenge.ui.screen.event_list

import com.example.ticketmasterdemochallenge.domain.model.EventDomain

/**
 * [EventListState] is a data class that wraps all the data states we need, related to the Events,
 * it contains the necessary information for the Composable function to consume it.
 * @param isLoading: represents the loading state of the data.
 * @param eventList: represents the list of Events that the app currently have.
 * */
data class EventListState(
    val isLoading: Boolean = false,
    val eventList: List<EventDomain> = emptyList(),
)
