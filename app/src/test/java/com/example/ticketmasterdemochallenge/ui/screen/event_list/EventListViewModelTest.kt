package com.example.ticketmasterdemochallenge.ui.screen.event_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.model.VenueDomain
import com.example.ticketmasterdemochallenge.domain.use_case.EventUseCases
import com.example.ticketmasterdemochallenge.domain.use_case.GetEventFromDatabaseUseCase
import com.example.ticketmasterdemochallenge.domain.use_case.GetEventsUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EventListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var eventUseCases: EventUseCases

    private lateinit var eventListViewModel: EventListViewModel

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

    private val stateFlow = MutableStateFlow(EventListState())

    @Mock
    private var getEventsUseCase = mockk<GetEventsUseCase>(relaxed = true) {
        coEvery { getEventsList(1) } returns listOf(eventDomain)
    }

    @Mock
    private var getEventFromDatabaseUseCase = mockk<GetEventFromDatabaseUseCase>(relaxed = true)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        eventUseCases = EventUseCases(
            getEventsUseCase,
            getEventFromDatabaseUseCase
        )
        eventListViewModel = EventListViewModel(eventUseCases)
    }

    @Test
    fun `testea la wea`() {
        runBlocking { eventListViewModel.getEventList() }
        assertThat(eventListViewModel.eventListState).isNotNull()
        assertThat(eventListViewModel.eventListState.value.eventList).isEqualTo(listOf(eventDomain))
    }
}