package org.ianus.revmeet.views.participants

import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel

class AddParticipantsPresenter(
    private val app: MainApp,
    private val meetingId: Long
) {
    fun meetingExists(): Boolean =
        app.meetings.findById(meetingId) != null

    //determine if the meeting is already at full capacity
    fun isAtCapacity(): Boolean {
        val meeting = app.meetings.findById(meetingId) ?: return false
        return meeting.numberOfParticipants >= meeting.maxNumberOfParticipants
    }

    fun getAvailableUsers(): List<UserModel> {
        val meeting = app.meetings.findById(meetingId) ?: return emptyList()

        if (meeting.numberOfParticipants >= meeting.maxNumberOfParticipants) {
            return emptyList()
        }

        val participantIds = meeting.participantUserIds.toSet()
        return app.users.findAll().filter { it.id !in participantIds }
    }

    fun addUser(userId: Long): Boolean {
        val meeting = app.meetings.findById(meetingId) ?: return false
        val user = app.users.findById(userId) ?: return false

        if (userId in meeting.participantUserIds) return false
        if (meeting.numberOfParticipants >= meeting.maxNumberOfParticipants) return false

        meeting.participantUserIds.add(user.id)
        app.meetings.update(meeting)
        return true
    }
}