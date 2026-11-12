package org.ianus.revmeet.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.ianus.revmeet.helper.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

private const val MEETING_JSON_FILE = "meetings.json"
private val meetingGsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
private val meetingListType: Type =
    object : TypeToken<ArrayList<MeetingModel>>() {}.type

private fun generateMeetingRandomId(): Long {
    return Random().nextLong()
}

class MeetingJSONStore(private val context: Context) : MeetingStore {

    var meetings = mutableListOf<MeetingModel>()

    init {
        if (exists(context, MEETING_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MeetingModel> {
        logAll()
        return meetings
    }

    override fun findById(id: Long): MeetingModel? {
        return meetings.find { it.id == id }
    }

    override fun create(meeting: MeetingModel) {
        meeting.id = generateMeetingRandomId()
        meetings.add(meeting)
        serialize()
    }

    override fun update(meeting: MeetingModel) {
        val foundMeeting = meetings.find { it.id == meeting.id }

        if (foundMeeting != null) {
            foundMeeting.title = meeting.title
            foundMeeting.description = meeting.description
            foundMeeting.typeOfLocation = meeting.typeOfLocation
            foundMeeting.maxNumberOfParticipants =
                meeting.maxNumberOfParticipants
            foundMeeting.startsAt = meeting.startsAt
            foundMeeting.organizerUserId = meeting.organizerUserId
            foundMeeting.participantUserIds =
                ArrayList(meeting.participantUserIds)
            foundMeeting.lat = meeting.lat
            foundMeeting.lng = meeting.lng
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = meetingGsonBuilder.toJson(meetings, meetingListType)
        write(context, MEETING_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, MEETING_JSON_FILE)
        meetings = meetingGsonBuilder.fromJson(jsonString, meetingListType)
    }

    override fun delete(meeting: MeetingModel) {
        meetings.remove(meeting)
        serialize()
    }

    private fun logAll() {
        meetings.forEach { Timber.i("$it") }
    }
}