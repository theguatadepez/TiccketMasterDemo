package com.example.ticketmasterdemochallenge.domain.network

import com.example.ticketmasterdemochallenge.data.model.event_response.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY: String = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"

/**
 * [EventAPI] is the Retrofit API call implementation.
 * */
interface EventAPI {

    /**
     * [getEventList] is the Get API call to retrieve a list of Events from the TicketMasterAPI.
     * @param apiKey: API Key value obtained from the const val [API_KEY]
     * */
    @GET("v2/events.json")
    suspend fun getEventList(
        @Query("apikey") apiKey:String = API_KEY
    ): Response<EventResponse>
}