package com.example.ticketmasterdemochallenge.ui.utils

/**
 * [ConnectionState] is a Sealed Class that contains two instance of ConnectionState.
 * @object Available represents an available state of the connection.
 * @object Unavailable represents an unavailable state of the connection.
 * */
sealed class ConnectionState {
    object Available: ConnectionState()
    object Unavailable: ConnectionState()
}
