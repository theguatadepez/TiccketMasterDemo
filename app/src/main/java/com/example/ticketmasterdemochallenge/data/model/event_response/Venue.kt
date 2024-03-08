package com.example.ticketmasterdemochallenge.data.model.event_response

data class Venue(
    val _links: Links,
    val address: Address,
    val city: City,
    val country: Country,
    val id: String,
    val locale: String,
    val location: Location,
    val markets: List<Market>,
    val name: String,
    val postalCode: String,
    val state: State,
    val test: Boolean,
    val timezone: String,
    val type: String
)