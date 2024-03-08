package com.example.ticketmasterdemochallenge.ui.screen.event_list

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ticketmasterdemochallenge.R
import com.example.ticketmasterdemochallenge.data.model.event_response.Image
import com.example.ticketmasterdemochallenge.domain.model.EventDomain
import com.example.ticketmasterdemochallenge.domain.model.VenueDomain
import com.example.ticketmasterdemochallenge.ui.utils.ConnectionState
import com.example.ticketmasterdemochallenge.ui.utils.currentConnectivityState
import com.example.ticketmasterdemochallenge.ui.utils.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * [EventListScreen] Represents the main view of the Application, contains the logic to react to the different states of the UI.
 * @param viewModel is a [EventListViewModel] instance, injected by Hilt.
 *
 * The App needs to be open at least once with internet connection in order to fill the Room Database and have a Local database.
 * if this is not the case and the app is first open without internet, you will see the [NetworkErrorDialogScreen] Screen.
 * */
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun EventListScreen(
    viewModel: EventListViewModel = hiltViewModel(),
) {
    val connectionState by connectivityState()
    val eventListState by viewModel.eventListState.collectAsStateWithLifecycle()
    val isConnected = connectionState === ConnectionState.Available
    if(!isConnected && eventListState.eventList.isEmpty()) {
        NetworkErrorDialogScreen()
    } else {
        if(eventListState.isLoading) {
            IndeterminateCircularIndicatorScreen()
        } else {
            EventsView(
                events = eventListState.eventList,
                viewModel = viewModel
            )
        }
    }
}

/**
 * [SearchBarView] Represents the Search Bar Compose component. It safe a local search history until the app is closed (intended),
 * also, it calls the API whenever the user clicks the x button.
 * @param eventsSearched: is the current list of events displayed in the UI.
 * @param viewModel: is the [EventListViewModel] instance.
 * @param paddingValues: Padding values to be injected from the father.
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarView(
    eventsSearched: List<EventDomain>,
    viewModel: EventListViewModel,
    paddingValues: PaddingValues
) {
    var textToSearch by remember { mutableStateOf("") }
    var activeSearch by remember { mutableStateOf(false) }
    val searchList = remember { mutableStateListOf<String>() }

    DockedSearchBar(
        query = textToSearch,
        onQueryChange = {
            textToSearch = it
        },
        onSearch = {
            //If we have not searched for the value, we add it to the list.
            if(!searchList.contains(textToSearch)) searchList.add(textToSearch)
            //We do the api call
            viewModel.filterEventList(textToSearch, eventsSearched)
            activeSearch = false
        },
        active = activeSearch,
        onActiveChange = {
            activeSearch = it
        },
        placeholder = { Text(stringResource(R.string.search_event_hint)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(R.string.search_icon_description),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        trailingIcon = {
            if(activeSearch) {
                Icon(
                    modifier = Modifier.clickable {
                        textToSearch = ""
                        activeSearch = false
                        viewModel.getEventList()
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(R.string.close_icon_description),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        tonalElevation = 0.dp,
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding() + 4.dp,
                bottom = paddingValues.calculateBottomPadding() + 4.dp,
            )
            .fillMaxWidth()
    ) {
            searchList.forEach {
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Icon(
                    modifier = Modifier.padding(end = 4.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = stringResource(R.string.history_icon_description),
                )
                Text(text = it, modifier = Modifier.clickable { textToSearch = it })
            }
        }
    }
}

/**
 * [EventCard] represent the view of a single item (Event) in the UI.
 * @param event: is the Event that needs to be show.
 * @param modifier: Modifier instance.
 * */
@Composable
fun EventCard(
    event: EventDomain,
    modifier: Modifier
) {
    EventCardText(event = event , modifier = modifier)
}

/**
 * [EventCardText] represent the text inside the [EventCard] in the UI, contains all the data of the Event that needs to be show.
 * @param event: is the Event that needs to be show.
 * @param modifier: Modifier instance.
 * */
@Composable
fun EventCardText(
    event: EventDomain,
    modifier: Modifier
) {

    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(event.images[0].url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = modifier
                    // Set image size to 100 dp
                    .size(100.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    // Adding border to the image and adjust the color of it.
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier =
                Modifier.padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 8.dp,
                    end = 8.dp
                )
            ) {
                Text(
                    text = event.name,
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = event.date,
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = event.venue.address,
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = event.venue.city,
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = event.venue.country,
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

/**
 * [EventsView] is the representation of the List of Events displayed in the Screen,
 * it contains all the UI Screens Composable, the TopAppBar, the Search Bar and the list of [EventCard]
 * @param modifier: Modifier instance.
 * @param events: is a list of Events that needs to be show.
 * @param viewModel: is an instance of the [EventListViewModel].
 * */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventsView(
    modifier: Modifier = Modifier,
    events: List<EventDomain> = List(3) {
        val images = Image(
            fallback = true,
            height = 0,
            ratio = "",
            url = "https://www.ticketmaster.com/charlotte-hornets-vs-phoenix-suns-charlotte-north-carolina-03-15-2024/event/2D005EF1EEFA6495",
            width = 120
        )
        EventDomain(
            id = "$it",
            name = "name_$it",
            genre = "genre",
            images = List(1) { images },
            url = "google.com",
            venue = VenueDomain(
                id = "",
                address = "Av Siempre viva",
                city = "Santiago",
                country = "Chile"
            ),
            date = "JAN 7"
        )
    },
    viewModel: EventListViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Simple TM Events List",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    ) { paddingValues ->
        SearchBarView(events, viewModel, paddingValues)

        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 72.dp,
                bottom = paddingValues.calculateBottomPadding() + 16.dp,
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items = events) { event ->
                EventCard(event = event, modifier = modifier)
            }
        }
    }
}

/**
 * [IndeterminateCircularIndicatorScreen] is an infinity circular progress indicator.
 * */
@Composable
fun IndeterminateCircularIndicatorScreen() {
    CircularProgressIndicator(
        modifier = Modifier
            .width(64.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colorScheme.secondary,
    )
}

/**
 * [NetworkErrorDialogScreen] is a Screen for a Network Error in the App.
 * */
@Composable
fun NetworkErrorDialogScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Center)
                .offset(y = (-50).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.SignalWifiConnectedNoInternet4,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = stringResource(id = R.string.NoInternetConnection),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(id = R.string.NoInternetConnectionSub),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

/**
 * [connectivityState] is a State Composable method that allows to check for the internet status in real time,
 * it upgrades the state value whenever the internet connection of the device change.
 * */
@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}
