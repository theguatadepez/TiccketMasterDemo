package com.example.ticketmasterdemochallenge.data.mapper

import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.data.model.room_entity.EventRoom
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.model.VenueDomain


/**
 * [toEventRoom] is an extended function that allows converting a list of [EventDomain] into a list of [EventRoom]
 * */
fun List<EventDomain>.toEventRoom(): List<EventRoom> =
    this.map { eventDomain ->
        EventRoom(
            id = eventDomain.id,
            name = eventDomain.name,
            genre = eventDomain.genre,
            images = eventDomain.images[0].url,
            url = eventDomain.url,
            date = eventDomain.date,
            venueId = eventDomain.venue.id,
            venueAddress = eventDomain.venue.address,
            venueCity = eventDomain.venue.city,
            venueCountry = eventDomain.venue.country,
        )
    }

/**
 * [toEventDomain] is an extended function that allows converting a list of [EventRoom] into a list of [EventDomain]
 * */
fun List<EventRoom>.toEventDomain(): List<EventDomain> =
    this.map {
        EventDomain(
            id = it.id,
            name = it.name,
            genre = it.genre,
            images = listOf(
                Image(
                    fallback = false,
                    height = 32,
                    ratio = "",
                    url = it.images,
                    width = 32
                )
            ) ,
            url = it.url,
            venue = VenueDomain(
                id = it.venueId,
                address = it.venueAddress,
                city = it.venueCity,
                country = it.venueCountry
            ),
            date = it.date,
        )
    }