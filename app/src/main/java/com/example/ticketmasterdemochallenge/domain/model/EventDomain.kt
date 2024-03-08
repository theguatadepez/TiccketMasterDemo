package com.example.ticketmasterdemochallenge.domain.model

import com.example.ticketmasterdemochallenge.data.model.event_response.Image

/**
 * [EventDomain] is the Domain Layer representation of an [Event] data class.
 * @param id: is the Id of the Event.
 * @param name: is the Name of the Event.
 * @param genre: is the Genre of the Event.
 * @param images: is a list of Images that belong to the Event.
 * @param url: contains some relevants Links of the Event.
 * @param venue: contains the Venue data of the Event, represented as a [VenueDomain].
 * @param date: is the date of the Event.
 * */
data class EventDomain(
    val id: String,
    val name: String,
    val genre: String,
    val images: List<Image>,
    val url: String,
    val venue: VenueDomain,
    val date: String,
)
