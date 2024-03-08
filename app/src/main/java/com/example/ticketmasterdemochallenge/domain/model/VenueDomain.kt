package com.example.ticketmasterdemochallenge.domain.model

/**
 * [VenueDomain] is the Domain Layer representation of a [Venue] data class.
 * @param id: is the Id of the Venue.
 * @param address: is the address of the Venue.
 * @param city: is the city of the Venue.
 * @param country: is the country of the Venue.
 * */
data class VenueDomain(
    val id: String,
    val address: String,
    val city: String,
    val country: String,
)