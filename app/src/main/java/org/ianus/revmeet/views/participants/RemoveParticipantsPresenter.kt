package org.ianus.revmeet.views.participants

import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel

class RemoveParticipantsPresenter(
    private val app: MainApp,
    private val meetingId: Long
) {
    fun meetingExists(): Boolean =
        app.meetings.findById(meetingId) != null

    fun getMeetingUsers(): List<UserModel> {
        val meeting = app.meetings.findById(meetingId) ?: return emptyList()
        val participantIds = meeting.participantUserIds.toSet()
        return app.users.findAll().filter { it.id in participantIds }
    }

    fun removeUser(userId: Long): Boolean {
        val meeting = app.meetings.findById(meetingId) ?: return false
        if (!meeting.participantUserIds.remove(userId)) return false

        app.meetings.update(meeting)
        return true
    }
}