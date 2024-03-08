package com.example.ticketmasterdemochallenge.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ticketmasterdemochallenge.data.model.event_response.EventResponse
import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.data.model.room_entity.EventRoom
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.model.VenueDomain
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * [toEventDomainList] is an extended function that allows converting a [EventResponse] into a list of [EventDomain]
 * */
@RequiresApi(Build.VERSION_CODES.O)
fun EventResponse.toEventDomainList(): List<EventDomain> =
    this._embedded
        .events
        .map { event ->
            EventDomain(
                id = event.id,
                name = event.name,
                genre = event.classifications[0].genre.name,
                images = event.images,
                url = event.url,
                venue = VenueDomain(
                    id = event._embedded.venues[0].id,
                    address = event._embedded.venues[0].address.line1,
                    city = event._embedded.venues[0].city.name,
                    country = event._embedded.venues[0].country.name,
                ),
                date = event.dates.start.localDate.dateFormatter()
            )
        }

/**
 * [dateFormatter] is an extended function that allows converting s Date String of patter [yyyy-MM-dd] into [MONTH DAYOFMONTH]
 *
 * Example, the original String "2024-12-31" is formatter to "DECEMBER 31"
 * */
@RequiresApi(Build.VERSION_CODES.O)
fun String.dateFormatter(): String {
    val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(this, firstApiFormat)
    return "${date.month} ${date.dayOfMonth}"
}
