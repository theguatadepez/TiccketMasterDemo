package com.example.ticketmasterdemochallenge.domain.use_case

/**
 * [EventUseCases] is a data class that contains instances of the UseCases related to the Events.
 * @param getEventsUseCase: is a [GetEventsUseCase] instance. Represent the useCase for the API call.
 * @param getEventFromDatabaseUseCase: is a [GetEventFromDatabaseUseCase] instance. Represent the useCase for the Room Database call.
 * */
data class EventUseCases(
    val getEventsUseCase: GetEventsUseCase,
    val getEventFromDatabaseUseCase: GetEventFromDatabaseUseCase
)
