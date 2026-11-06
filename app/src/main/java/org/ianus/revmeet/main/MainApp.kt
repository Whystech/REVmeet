package org.ianus.revmeet.main

import android.app.Application
import org.ianus.revmeet.models.CarMemStore
import org.ianus.revmeet.models.CarStore
import org.ianus.revmeet.models.MeetingStore
import org.ianus.revmeet.models.MeetingMemStore
import org.ianus.revmeet.models.MeetingModel
import org.ianus.revmeet.models.UserMemStore
import org.ianus.revmeet.models.UserModel
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

        val oneDayMillis = 24 * 60 * 60 * 1000L
        val firstMeetingTime = System.currentTimeMillis()

        meetings = MeetingMemStore().apply {
            create(MeetingModel(
                title = "Coastal Sunrise Run",
                description = "A relaxed morning drive along the coast.",
                typeOfLocation = "Coastline",
                maxNumberOfParticipants = 12,
                startsAt = firstMeetingTime + oneDayMillis
            ))
            create(MeetingModel(
                title = "Mountain Pass Cruise",
                description = "Meet up for a scenic drive through the mountain roads.",
                typeOfLocation = "Mountain path",
                maxNumberOfParticipants = 10,
                startsAt = firstMeetingTime + 2 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Coffee and Cars",
                description = "Show your car and grab a coffee with other enthusiasts.",
                typeOfLocation = "Cafe",
                maxNumberOfParticipants = 20,
                startsAt = firstMeetingTime + 3 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Garage Open Day",
                description = "An informal afternoon meeting at a local garage.",
                typeOfLocation = "Garage",
                maxNumberOfParticipants = 8,
                startsAt = firstMeetingTime + 4 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Track Day Meetup",
                description = "Meet at the track before the afternoon sessions.",
                typeOfLocation = "Track",
                maxNumberOfParticipants = 15,
                startsAt = firstMeetingTime + 5 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Beachside Car Meet",
                description = "An evening meetup by the beach.",
                typeOfLocation = "Beach",
                maxNumberOfParticipants = 18,
                startsAt = firstMeetingTime + 6 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Sunday Viewpoint Drive",
                description = "A group drive ending at a scenic viewpoint.",
                typeOfLocation = "Viewpoint",
                maxNumberOfParticipants = 10,
                startsAt = firstMeetingTime + 7 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Classic Cars Gathering",
                description = "Bring a classic car or come along to have a look.",
                typeOfLocation = "Parking lot",
                maxNumberOfParticipants = 25,
                startsAt = firstMeetingTime + 8 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Country Road Cruise",
                description = "A casual drive through the countryside.",
                typeOfLocation = "Street",
                maxNumberOfParticipants = 12,
                startsAt = firstMeetingTime + 9 * oneDayMillis
            ))
            create(MeetingModel(
                title = "Off-Road Morning",
                description = "A small group meetup for an off-road route.",
                typeOfLocation = "Off-road",
                maxNumberOfParticipants = 6,
                startsAt = firstMeetingTime + 10 * oneDayMillis
            ))
        }

        users = UserMemStore().apply {
            create(UserModel(
                username = "alexdriver",
                email = "alex@example.com",
                displayName = "Alex Morgan",
                bio = "Enjoys scenic drives and classic cars."
            ))
            create(UserModel(
                username = "jordancar",
                email = "jordan@example.com",
                displayName = "Jordan Lee",
                bio = "Weekend driver and car photographer."
            ))
            create(UserModel(
                username = "samwheels",
                email = "sam@example.com",
                displayName = "Sam Rivera",
                bio = "Interested in track days and motorsport."
            ))
            create(UserModel(
                username = "taylorroad",
                email = "taylor@example.com",
                displayName = "Taylor Brooks",
                bio = "Loves exploring new roads."
            ))
            create(UserModel(
                username = "caseyclassic",
                email = "casey@example.com",
                displayName = "Casey Quinn",
                bio = "Classic car enthusiast."
            ))
            create(UserModel(
                username = "morganmotor",
                email = "morgan@example.com",
                displayName = "Morgan Blake",
                bio = "Always happy to talk cars."
            ))
            create(UserModel(
                username = "rileycruise",
                email = "riley@example.com",
                displayName = "Riley Chen",
                bio = "Enjoys relaxed group drives."
            ))
            create(UserModel(
                username = "jamiegear",
                email = "jamie@example.com",
                displayName = "Jamie Patel",
                bio = "Learning about car maintenance and detailing."
            ))
            create(UserModel(
                username = "drewroadster",
                email = "drew@example.com",
                displayName = "Drew Wilson",
                bio = "Road trip fan and convertible owner."
            ))
            create(UserModel(
                username = "averyauto",
                email = "avery@example.com",
                displayName = "Avery Kim",
                bio = "Interested in meeting local car enthusiasts."
            ))
        }
    }
}