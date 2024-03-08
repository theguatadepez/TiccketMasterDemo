package com.example.ticketmasterdemochallenge.data.model.event_response

data class EmbeddedX(
    val attractions: List<Attraction>,
    val venues: List<Venue>
)