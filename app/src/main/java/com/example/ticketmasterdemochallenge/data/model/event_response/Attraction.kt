package com.example.ticketmasterdemochallenge.data.model.event_response

data class Attraction(
    val _links: Links,
    val classifications: List<Classification>,
    val id: String,
    val images: List<Image>,
    val locale: String,
    val name: String,
    val test: Boolean,
    val type: String
)