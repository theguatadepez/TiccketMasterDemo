package com.example.ticketmasterdemochallenge.data.model.event_response

data class Event(
    val _embedded: EmbeddedX,
    val _links: LinksXX,
    val classifications: List<Classification>,
    val dates: Dates,
    val id: String,
    val images: List<Image>,
    val locale: String,
    val name: String,
    val promoter: Promoter,
    val sales: Sales,
    val test: Boolean,
    val type: String,
    val url: String
)