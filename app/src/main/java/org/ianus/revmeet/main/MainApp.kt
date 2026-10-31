package org.ianus.revmeet.main

import android.app.Application
import org.ianus.revmeet.models.MeetingStore
import org.ianus.revmeet.models.MeetingMemStore
import org.ianus.revmeet.models.MeetingModel
import timber.log.Timber.i
import timber.log.Timber

class MainApp : Application() {

    lateinit var meetings: MeetingStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        meetings = MeetingMemStore()
        i("REVmeet started")

        meetings = MeetingMemStore().apply {
            create(
                MeetingModel(
                    title = "Saturday drive",
                    description = "Strolling along the coast for a few hours.",
                    typeOfLocation = "Starting point",
                    numberOfParticipants = 3,
                    maxNumberOfParticipants = 8
                )
            )
            create(
                MeetingModel(
                    title = "Sunday drive",
                    description = "Strolling along the coast for a few hours.",
                    typeOfLocation = "Starting point",
                    numberOfParticipants = 3,
                    maxNumberOfParticipants = 8
                )
            )
            create(
                MeetingModel(
                    title = "Monday drive",
                    description = "Strolling along the coast for a few hours.",
                    typeOfLocation = "Starting point",
                    numberOfParticipants = 3,
                    maxNumberOfParticipants = 8
                )
            )
        }
    }
}