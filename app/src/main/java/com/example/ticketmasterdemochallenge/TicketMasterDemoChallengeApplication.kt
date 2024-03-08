package com.example.ticketmasterdemochallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * [TicketMasterDemoChallengeApplication] is a subClass of [Application], needed to use DI.
 * */
@HiltAndroidApp
class TicketMasterDemoChallengeApplication: Application()