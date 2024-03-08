package com.example.ticketmasterdemochallenge.data.model.room_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [EventRoom] is the Data layer Entity for the Room Database that represents an Event.
 * */
@Entity(tableName = "event_table")
data class EventRoom(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val genre: String,
    val images: String,
    val url: String,
    val date: String,
    val venueId: String,
    val venueAddress: String,
    val venueCity: String,
    val venueCountry: String,
)
