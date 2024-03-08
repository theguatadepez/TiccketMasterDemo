package com.example.ticketmasterdemochallenge.data.model.event_response

data class Start(
    val dateTBA: Boolean,
    val dateTBD: Boolean,
    val localDate: String,
    val noSpecificTime: Boolean,
    val timeTBA: Boolean
)