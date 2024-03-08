package com.example.ticketmasterdemochallenge.data.model.event_response

data class Classification(
    val genre: Genre,
    val primary: Boolean,
    val segment: Segment,
    val subGenre: SubGenre
)