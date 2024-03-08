package com.example.ticketmasterdemochallenge.data.model.event_response

/**
 * [EventResponse] is the returned response from the call to the TicketMasterAPI, it represents the Data Layer model.
 * */
data class EventResponse(
    val _embedded: Embedded,
    val _links: LinksXXX,
    val page: Page
)