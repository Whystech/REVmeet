package org.ianus.revmeet.views.meeting

import android.app.Activity
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.MeetingModel

class MeetingPresenter(private val view: MeetingView) {

    private val app = view.application as MainApp
    private val meetingToEdit: MeetingModel? =
        view.intent.getParcelableExtra("meeting_edit")

    val isEditing: Boolean
        get() = meetingToEdit != null

    init {
        meetingToEdit?.let(view::showMeeting)
    }

    fun doSaveMeeting(
        title: String,
        description: String,
        locationType: String,
        maximum: Int,
        startsAt: Long
    ) {
        val existingMeeting = meetingToEdit

        if (existingMeeting == null) {
            app.meetings.create(
                MeetingModel(
                    title = title,
                    description = description,
                    typeOfLocation = locationType,
                    maxNumberOfParticipants = maximum,
                    startsAt = startsAt
                )
            )
        } else {
            existingMeeting.title = title
            existingMeeting.description = description
            existingMeeting.typeOfLocation = locationType
            existingMeeting.maxNumberOfParticipants = maximum
            existingMeeting.startsAt = startsAt
            app.meetings.update(existingMeeting)
        }

        view.setResult(Activity.RESULT_OK)
        view.finish()
    }

    fun doDeleteMeeting() {
    val meeting = meetingToEdit ?: return
    app.meetings.delete(meeting)
    view.setResult(Activity.RESULT_OK)
    view.finish()
}
}