package com.example.ticketmasterdemochallenge.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ticketmasterdemochallenge.data.mapper.toEventDomainList
import com.example.ticketmasterdemochallenge.data.mapper.toEventRoom
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.network.ApiException
import com.example.ticketmasterdemochallenge.domain.network.EventAPI
import com.example.ticketmasterdemochallenge.domain.network.EventClientRepository
import com.example.ticketmasterdemochallenge.domain.network.EventDao
import com.example.ticketmasterdemochallenge.domain.network.SafeAPIRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * [EventClientRepositoryImpl] is the Repository implementation of the App, related to any action that involves Events.
 * */
class EventClientRepositoryImpl @Inject constructor(
    private val eventAPI: EventAPI,
    private val eventDao: EventDao
): EventClientRepository, SafeAPIRequest() {

    /**
     * [getEvents] is a suspend method that allows retrieve Events from the TicketMaster API.
     * @param limit: is the limit of response we want to do, currently it allows 1.
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEvents(limit: Int): List<EventDomain>? {
        return try {
            val eventDomainList = apiRequest { eventAPI.getEventList() }.toEventDomainList()
            withContext(Dispatchers.IO) {
                eventDomainList.toEventRoom().forEach {
                    eventDao.insertEvent(it)
                }
            }
            eventDomainList
        }catch (apiException: ApiException) {
            null
        }
    }

    /**
     * [getEventsFromDatabase] is a method that allows to retrieve Events from the Room Database.
     * */
    override fun getEventsFromDatabase() = eventDao.getAllEvents()
}