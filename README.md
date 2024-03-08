TicketMaster API Clean Architecture Demo.

This App is intended to consume the https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events-v2 TicketMaster API, in order to obtain Events and their data, to be displayed in the App.

The App contain a Main Screen, which shows 20 Events, and a Seach Bar to search for an specific Event within that list of Events. The search bar contain a Local Search History that gets deleted once you re-open the App and triggers getting the list of Events when clicking the X icon.

This App was made with Clean Architecture and uses the following libraries, to name a few:

- Jetpack Compose (with states as Obserbable objects).
- Hilt/Dagger.
- Retrofit2.
- Room database for Local Storage.
- Material 3 Ui Components.
- Coil for Images.

Features and how it works:

The App works with uiStates, which is a normal Observable implementaiton for Compose, this involves two states which represents if the App is getting data or not, there are multiple scenarios involving this:

- If the App is opened the first time, it will save the list of Events in a Room Database, then if the App looses internet connection, if the user try to get a list of items without internet, the App will get the data from the Room Database (Please notice that when the App gets data from the Database, the order is inverted, I did this on purposes so we can differentiate via UI the room Database data from the API one).
- If the App is opened the first time without internet, you will see an error Screen that tells the user that there is an error and there is no internet available, you need to turn on the internet to get the data.

Key Features:

- The UI is responsive since we are using Compose Components to create it, also works with Dark mode from the beginning.
- The App will check for Internet Status automatically, and display the correct screen accordingly.
- The Search Bar save a local history of searches until the app is restarted, this is intended since a persistent implementation can be overkill.
- The Search Bar x button needs to be clicked in order to revert the filter and see the entire list of Events.

Link of interest:

TicketMaster development DOCs -> https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events-v2


