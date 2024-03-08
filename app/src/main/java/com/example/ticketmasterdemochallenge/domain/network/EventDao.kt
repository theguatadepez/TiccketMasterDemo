package com.example.ticketmasterdemochallenge.domain.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.ticketmasterdemochallenge.data.model.room_entity.EventRoom
import kotlinx.coroutines.flow.Flow

/**
 * [EventDao] is the interface representation of the contract for the Room Database, involves every action we can do in the database.
 * */
@Dao
interface EventDao {
    /**
     * [insertEvent] Inserts an [EventRoom] object into the Database. If the object already exist, it is replaced.
     * @param event: Represents the [EventRoom] object that we want to insert into the database.
     * */
    @Insert(onConflict = REPLACE)
    suspend fun insertEvent(event: EventRoom)

    /**
     * [getAllEvents] get a list of [EventRoom] objects from the Database. Order by genre descending,
     * please notice that this descending order is intended to differentiate the Room responses from the API responses,
     * his way we can see in the UI when we get the data from locally or from the API (for showing purposes only).
     * */
    @Query("select * from event_table order by genre desc")
    fun getAllEvents(): Flow<List<EventRoom>>

}