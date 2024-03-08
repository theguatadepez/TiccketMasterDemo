package com.example.ticketmasterdemochallenge.domain.use_case

import com.example.ticketmasterdemochallenge.domain.network.EventClientRepository

/**
 * [GetEventFromDatabaseUseCase] Represent the useCase for the Room Database call.
 * @param eventClientRepository: is the repository instance of the [EventClientRepository]
 * */
class GetEventFromDatabaseUseCase(
    private val eventClientRepository: EventClientRepository
) {
    /**
     * [getEventListFromDatabase] function gets a list of events from the Data Layer and returned to the UI Layer as a Flow.
     * */
    fun getEventListFromDatabase() = eventClientRepository.getEventsFromDatabase()
}