package com.example.ticketmasterdemochallenge.data.model.room_entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ticketmasterdemochallenge.domain.network.EventDao

/**
 * Abstract Implementation of the Room Database.
 * */
@Database(entities = [EventRoom::class], version = 1)
abstract class EventDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}