package com.example.ticketmasterdemochallenge.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import com.example.ticketmasterdemochallenge.domain.network.EventAPI
import com.example.ticketmasterdemochallenge.domain.network.EventDao
import com.example.ticketmasterdemochallenge.domain.network.SafeAPIRequest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class EventClientAPITest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private var eventAPI: EventAPI = mockk(relaxed = true)

    @MockK
    private var apiRequest: SafeAPIRequest = mockk {
        coEvery { apiRequest { eventAPI.getEventList()} } returns eventResponse
    }

    @MockK
    private var eventDao: EventDao = mockk {
        coEvery { insertEvent(any()) } just Runs
    }

    @InjectMockKs
    private lateinit var eventClientAPI: EventClientRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        eventClientAPI = EventClientRepositoryImpl(eventAPI, eventDao)
    }

    @Test
    fun `get events list`() {
        runBlocking { eventClientAPI.getEvents(1) }
        coVerify(exactly = 1) { eventAPI.getEventList() }
        coVerify(exactly = 1) { eventDao.insertEvent(any()) }
    }

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
}