package com.example.ticketmasterdemochallenge.domain.use_case

import com.example.ticketmasterdemochallenge.domain.network.EventClientRepository

/**
 * [GetEventsUseCase] Represent the useCase for the Room Database call.
 * @param eventClientRepository: is the repository instance of the [EventClientRepository]
 * */
class GetEventsUseCase(
    private val eventClientRepository: EventClientRepository
) {
    /**
     * [getEventsList] function gets a list of events from the Data Layer and returned to the UI Layer as a List of [EventDomain].
     * */
    suspend fun getEventsList(limit: Int) = eventClientRepository.getEvents(limit)
}