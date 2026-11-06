package org.ianus.revmeet.models

import org.ianus.revmeet.models.MeetingModel
import org.ianus.revmeet.models.MeetingStore
import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MeetingMemStore : MeetingStore {
    val meetings = ArrayList<MeetingModel>()

    override fun findAll(): List<MeetingModel> {
        return meetings
    }

    override fun findById(id:Long) : MeetingModel? {
        val foundMeeting: MeetingModel? = meetings.find { it.id == id }
        return foundMeeting
    }

    override fun create(meeting: MeetingModel) {
        meeting.id = getId()
        meetings.add(meeting)
        logAll()
    }

    ///added additional update possibilities as the ing has extended properties
    override fun update(meeting: MeetingModel) {
        val foundMeeting: MeetingModel? = meetings.find { p -> p.id == meeting.id }
        if (foundMeeting != null) {
            val participantIds = meeting.participantUserIds.toList()
            foundMeeting.participantUserIds.clear()
            foundMeeting.participantUserIds.addAll(participantIds)
            foundMeeting.title = meeting.title
            foundMeeting.description = meeting.description
            foundMeeting.lat = meeting.lat
            foundMeeting.lng = meeting.lng
            foundMeeting.maxNumberOfParticipants = meeting.maxNumberOfParticipants
            foundMeeting.typeOfLocation = meeting.typeOfLocation
            foundMeeting.organizerUserId = meeting.organizerUserId
            foundMeeting.startsAt = meeting.startsAt
            logAll()
        }
    }

    private fun logAll() {
        meetings.forEach { i("$it") }
    }

    override fun delete(meeting: MeetingModel) {
        meetings.remove(meeting)
    }
}