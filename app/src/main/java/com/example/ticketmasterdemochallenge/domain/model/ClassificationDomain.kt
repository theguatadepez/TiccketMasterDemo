package com.example.ticketmasterdemochallenge.domain.model

/**
 * [ClassificationDomain] is the Domain Layer representation of the [Classification] data class.
 * @param genre: is the Genre of the Event.
 * @param segment: is the Segment of the Event.
 * @param subGenre: is the subGenre of the Event.
 * */
data class ClassificationDomain(
    val genre: String,
    val segment: String,
    val subGenre: String,
)
