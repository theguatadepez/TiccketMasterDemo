package com.example.ticketmasterdemochallenge.data.mapper


import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.data.model.room_entity.EventRoom
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.model.VenueDomain
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class EventDaoMapperTest {

    private var eventRoom = EventRoom(
        id = "id",
        name = "name",
        genre = "genre",
        images = "imagesUrl",
        url = "url",
        date = "date",
        venueId = "venueId",
        venueAddress = "venueAddress",
        venueCity = "venueCity",
        venueCountry = "venueCountry",
    )

    private var eventDomain = EventDomain(
            id = "id",
            name = "name",
            genre = "genre",
            images = listOf(
                Image(
                    fallback = false,
                    height = 10,
                    ratio = "",
                    url = "imagesUrl",
                    width = 10
                )
            ),
            url = "url",
            date = "date",
            venue = VenueDomain(
                id = "venueId",
                address = "venueAddress",
                city = "venueCity",
                country = "venueCountry"
            ),
        )

    @Test
    fun `toEventRoom extension function returns correct eventDomain`() {
        listOf(eventRoom).toEventDomain().let {
            assertThat(it.size).isEqualTo(1)
            assertThat(it[0].id).isEqualTo(eventRoom.id)
            assertThat(it[0].url).isEqualTo(eventRoom.url)
            assertThat(it[0].date).isEqualTo(eventRoom.date)
            assertThat(it[0].images[0].url).isEqualTo(eventRoom.images)
            assertThat(it[0].venue.id).isEqualTo(eventRoom.venueId)
            assertThat(it[0].venue.address).isEqualTo(eventRoom.venueAddress)
            assertThat(it[0].venue.city).isEqualTo(eventRoom.venueCity)
            assertThat(it[0].venue.country).isEqualTo(eventRoom.venueCountry)
        }
    }

    @Test
    fun `toEventDomain extension function returns correct eventRoom`() {
        listOf(eventDomain).toEventRoom().let {
            assertThat(it.size).isEqualTo(1)
            assertThat(it[0].id).isEqualTo(eventRoom.id)
            assertThat(it[0].url).isEqualTo(eventRoom.url)
            assertThat(it[0].date).isEqualTo(eventRoom.date)
            assertThat(it[0].images).isEqualTo(eventRoom.images)
            assertThat(it[0].venueId).isEqualTo(eventRoom.venueId)
            assertThat(it[0].venueAddress).isEqualTo(eventRoom.venueAddress)
            assertThat(it[0].venueCity).isEqualTo(eventRoom.venueCity)
            assertThat(it[0].venueCountry).isEqualTo(eventRoom.venueCountry)
        }
    }
}