package org.ianus.revmeet.views.meeting

import android.app.Activity
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.MeetingModel

class MeetingPresenter(private val view: MeetingView) {

    private val app = view.application as MainApp

    fun doAddMeeting(
        title: String,
        description: String,
        locationType: String,
        participants: Int,
        maximum: Int
    ) {
        val meeting = MeetingModel(
            title = title,
            description = description,
            typeOfLocation = locationType,
            numberOfParticipants = participants,
            maxNumberOfParticipants = maximum
        )

        app.meetings.create(meeting)
        view.setResult(Activity.RESULT_OK)
        view.finish()
    }
}