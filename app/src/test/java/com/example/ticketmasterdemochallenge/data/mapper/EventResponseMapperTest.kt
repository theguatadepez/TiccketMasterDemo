package com.example.ticketmasterdemochallenge.data.mapper

import com.example.ticketmasterdemochallenge.data.model.event_response.Address
import com.example.ticketmasterdemochallenge.data.model.event_response.Attraction
import com.example.ticketmasterdemochallenge.data.model.event_response.AttractionX
import com.example.ticketmasterdemochallenge.data.model.event_response.City
import com.example.ticketmasterdemochallenge.data.model.event_response.Classification
import com.example.ticketmasterdemochallenge.data.model.event_response.Country
import com.example.ticketmasterdemochallenge.data.model.event_response.Dates
import com.example.ticketmasterdemochallenge.data.model.event_response.Embedded
import com.example.ticketmasterdemochallenge.data.model.event_response.EmbeddedX
import com.example.ticketmasterdemochallenge.data.model.event_response.Event
import com.example.ticketmasterdemochallenge.data.model.event_response.EventResponse
import com.example.ticketmasterdemochallenge.data.model.event_response.Genre
import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.data.model.event_response.Links
import com.example.ticketmasterdemochallenge.data.model.event_response.LinksXX
import com.example.ticketmasterdemochallenge.data.model.event_response.LinksXXX
import com.example.ticketmasterdemochallenge.data.model.event_response.Location
import com.example.ticketmasterdemochallenge.data.model.event_response.Market
import com.example.ticketmasterdemochallenge.data.model.event_response.Next
import com.example.ticketmasterdemochallenge.data.model.event_response.Page
import com.example.ticketmasterdemochallenge.data.model.event_response.Promoter
import com.example.ticketmasterdemochallenge.data.model.event_response.Public
import com.example.ticketmasterdemochallenge.data.model.event_response.Sales
import com.example.ticketmasterdemochallenge.data.model.event_response.Segment
import com.example.ticketmasterdemochallenge.data.model.event_response.Self
import com.example.ticketmasterdemochallenge.data.model.event_response.SelfXXX
import com.example.ticketmasterdemochallenge.data.model.event_response.Start
import com.example.ticketmasterdemochallenge.data.model.event_response.State
import com.example.ticketmasterdemochallenge.data.model.event_response.Status
import com.example.ticketmasterdemochallenge.data.model.event_response.SubGenre
import com.example.ticketmasterdemochallenge.data.model.event_response.Venue
import com.example.ticketmasterdemochallenge.data.model.event_response.VenueX
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EventResponseMapperTest {

    private val eventResponse = EventResponse(
        _embedded = Embedded(
            events = listOf(
                Event(
                    _embedded = EmbeddedX(
                        attractions = listOf(
                            Attraction(
                                _links = Links(
                                    self = Self(
                                        href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us"
                                    )
                                ),
                                classifications = listOf(
                                    Classification(
                                        genre = Genre(
                                            id = "genreId",
                                            name = "genreName"
                                        ),
                                        primary = true,
                                        segment = Segment(
                                            id = "segmentId",
                                            name = "segmentName"
                                        ),
                                        subGenre = SubGenre(
                                            id = "subGenreId",
                                            name = "subGenreName"
                                        )
                                    )
                                ),
                                id = "attractionId",
                                images = listOf(
                                    Image(
                                        fallback = false,
                                        height = 10,
                                        ratio = "",
                                        url = "url",
                                        width = 10
                                    )
                                ),
                                locale = "US",
                                name = "attractionName",
                                test = false,
                                type = "type"
                            )
                        ),
                        venues = listOf(
                            Venue(
                                _links = Links(
                                    self = Self(
                                        href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us"
                                    )
                                ),
                                address = Address(
                                    line1 = "4400 NW 87th Avenue"
                                ),
                                name = "Trump National Doral",
                                type = "venue",
                                id = "KovZpZAaEldA",
                                test = false,
                                locale = "en-us",
                                postalCode = "33178",
                                timezone = "America/New_York",
                                city = City(
                                    name = "Miami"
                                    ),
                                state = State(
                                    name = "Florida",
                                    stateCode = "FL"
                                ),
                                country = Country(
                                    name = "United States Of America",
                                    countryCode = "US"
                                ),
                                location = Location(
                                    longitude = "-80.33854298",
                                    latitude = "25.81260379"
                                ),
                                markets = listOf(
                                    Market(
                                        id = "15"
                                    )
                                ),
                            )
                        )
                    ),
                    _links = LinksXX(
                        attractions = listOf(
                            AttractionX(
                                href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us"
                            )
                        ),
                        self = Self(
                            href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us"
                        ),
                        venues = listOf(
                            VenueX(
                                href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us"
                            )
                        )
                    ),
                    classifications = listOf(
                        Classification(
                            genre = Genre(
                                id = "genreId",
                                name = "genreName"
                            ),
                            primary = true,
                            segment = Segment(
                                id = "segmentId",
                                name = "segmentName"
                            ),
                            subGenre = SubGenre(
                                id = "subGenreId",
                                name = "subGenreName"
                            )
                        )
                    ),
                    dates = Dates(
                        start = Start(
                            localDate = "2016-03-06",
                            dateTBD = false,
                            dateTBA = false,
                            timeTBA = true,
                            noSpecificTime = false
                        ),
                        timezone = "America/New_York",
                        status = Status(
                            code = "offsale"
                        )
                    ),
                    id = "id",
                    images = listOf(
                        Image(
                            fallback = false,
                            height = 10,
                            ratio = "",
                            url = "url",
                            width = 10
                        )
                    ),
                    locale = "US",
                    name = "name",
                    promoter = Promoter(
                        id = "682"
                    ),
                    sales = Sales(
                        public = Public(
                            startDateTime = "2015-10-02T11:00:00Z",
                            startTBD = false,
                            endDateTime = "2016-03-06T23:00:00Z"
                        )
                    ),
                    test = false,
                    type = "type",
                    url = "url"
                )
            )
        ),
        _links = LinksXXX(
            next = Next(
                href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us",
                templated = false
            ),
            self = SelfXXX(
                href = "/discovery/v2/attractions/K8vZ917uc57?locale=en-us",
                templated = false
            )
        ),
        page = Page(
            number = 1,
            size = 20,
            totalElements = 20,
            totalPages = 1
        )
    )

    @Test
    fun `toEventDomainList extension function returns correct eventDomain`() {
        eventResponse.toEventDomainList().let {
            assertThat(it.size).isEqualTo(1)
            assertThat(it[0].id).isEqualTo(eventResponse._embedded.events[0].id)
            assertThat(it[0].url).isEqualTo(eventResponse._embedded.events[0].url)
            assertThat(it[0].images[0].url).isEqualTo(eventResponse._embedded.events[0].images[0].url)
            assertThat(it[0].venue.id).isEqualTo(eventResponse._embedded.events[0]._embedded.venues[0].id)
            assertThat(it[0].venue.address).isEqualTo(eventResponse._embedded.events[0]._embedded.venues[0].address.line1)
            assertThat(it[0].venue.city).isEqualTo(eventResponse._embedded.events[0]._embedded.venues[0].city.name)
            assertThat(it[0].venue.country).isEqualTo(eventResponse._embedded.events[0]._embedded.venues[0].country.name)
        }
    }

    @Test
    fun `dateFormatter extension function returns correct formatter`() {
        "2024-12-31".dateFormatter().let {
            assertThat(it).isEqualTo("DECEMBER 31")
        }
    }
}