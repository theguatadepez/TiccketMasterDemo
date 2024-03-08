package com.example.ticketmasterdemochallenge.data.di

import android.content.Context
import androidx.room.Room
import com.example.ticketmasterdemochallenge.data.model.room_entity.EventDatabase
import com.example.ticketmasterdemochallenge.data.network.EventClientRepositoryImpl
import com.example.ticketmasterdemochallenge.domain.network.EventAPI
import com.example.ticketmasterdemochallenge.domain.network.EventClientRepository
import com.example.ticketmasterdemochallenge.domain.network.EventDao
import com.example.ticketmasterdemochallenge.domain.use_case.EventUseCases
import com.example.ticketmasterdemochallenge.domain.use_case.GetEventFromDatabaseUseCase
import com.example.ticketmasterdemochallenge.domain.use_case.GetEventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val BASE_URL = "https://app.ticketmaster.com/discovery/"

/**
 * [AppModule] is the main module and ins used to provide all the different component for DI.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) : EventDatabase {

        return Room.databaseBuilder(
            app,
            EventDatabase::class.java,
            "mydb.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSourceDao(db: EventDatabase) : EventDao {
        return db.eventDao()
    }
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): EventAPI =
        retrofit.create(EventAPI::class.java)

    @Provides
    @Singleton
    fun provideEventClient(
        api: EventAPI,
        dao: EventDao,
    ): EventClientRepository =
        EventClientRepositoryImpl(api, dao)

    @Provides
    @Singleton
    fun provideEventUseCases(
        eventClient: EventClientRepository,
    ): EventUseCases =
        EventUseCases(
            getEventsUseCase = GetEventsUseCase(eventClientRepository = eventClient),
            getEventFromDatabaseUseCase = GetEventFromDatabaseUseCase(eventClientRepository = eventClient)
        )


}