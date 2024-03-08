package com.example.ticketmasterdemochallenge.domain.network

import com.example.ticketmasterdemochallenge.data.model.room_entity.EventRoom
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import kotlinx.coroutines.flow.Flow

/**
 * [EventClientRepository] is the interface that represent the Contracts of the Repository to be implemented.
 * */
interface EventClientRepository {
    suspend fun getEvents(limit: Int): List<EventDomain>?
    fun getEventsFromDatabase(): Flow<List<EventRoom>>
}