package com.example.ticketmasterdemochallenge.ui.screen.event_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmasterdemochallenge.data.mapper.toEventDomain
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.use_case.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [EventListViewModel] the ViewModel class that represents the actions we want to do related to Events.
 * @param useCases: represents the [EventUseCases] instance and is injected via DI
 * */
@HiltViewModel
class EventListViewModel @Inject constructor(
    private val useCases: EventUseCases
): ViewModel() {

    private val _eventListState = MutableStateFlow(EventListState())
    val eventListState = _eventListState.asStateFlow()

    init {
        // We trigger the initial state of loading.
        _eventListState.update {
            it.copy(
                isLoading = true
            )
        }
        getEventList()
    }

    /**
     * [filterEventList] is a method that filters the list of Events given a certain keyword.
     * It returns a new [_eventListState], triggering the observables objects.
     * @param keyword: is the key word to filter.
     * @param eventList: is the List of Events that its going to be filtered.
     * */
    fun filterEventList(keyword: String, eventList: List<EventDomain>) {
        viewModelScope.launch {
            eventList.filter { it.name.contains(keyword.removeNewLine()) }.apply {
                _eventListState.update {
                    it.copy(
                        isLoading = false,
                        eventList = this
                    )
                }
            }
        }
    }

    /**
     * [getEventList] is a method that gets a list of Events, if the API response is empty, it search for data in the local database.
     * It returns a new [_eventListState], triggering the observables objects.
     * */
    fun getEventList() {
        viewModelScope.launch {
            useCases.getEventsUseCase.getEventsList(1)?.let { eventDomainList ->
                _eventListState.update {
                    it.copy(
                        isLoading = false,
                        eventList = eventDomainList
                    )
                }
            } ?: getEventsFromDatabase()
        }
    }

    /**
     * [getEventsFromDatabase] is a method that gets a list of Events from the local database.
     * It returns a new [_eventListState], triggering the observables objects.
     * */
    private fun getEventsFromDatabase() {
        viewModelScope.launch {
            useCases.getEventFromDatabaseUseCase.getEventListFromDatabase().flowOn(Dispatchers.IO).collect { eventRoomList ->
                _eventListState.update {
                    it.copy(
                        isLoading = false,
                        eventList = eventRoomList.toEventDomain()
                    )
                }
            }
        }
    }

    /**
     * [removeNewLine] is an extended method that removes a new Line from a String.
     * */
    private fun String.removeNewLine(): String = this.replace("\n","")
}