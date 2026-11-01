package org.ianus.revmeet.main

import android.app.Application
import org.ianus.revmeet.models.CarMemStore
import org.ianus.revmeet.models.CarStore
import org.ianus.revmeet.models.MeetingStore
import org.ianus.revmeet.models.MeetingMemStore
import org.ianus.revmeet.models.MeetingModel
import org.ianus.revmeet.models.UserMemStore
import org.ianus.revmeet.models.UserStore
import timber.log.Timber.i
import timber.log.Timber

class MainApp : Application() {

    lateinit var meetings: MeetingStore
    lateinit var users: UserStore
    lateinit var cars: CarStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        meetings = MeetingMemStore()
        users = UserMemStore()
        cars = CarMemStore()
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