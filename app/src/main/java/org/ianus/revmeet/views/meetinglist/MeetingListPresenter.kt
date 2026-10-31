package org.ianus.revmeet.views.meetinglist

import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.MeetingModel

class MeetingListPresenter(view: MeetingListView) {

    private val app = view.application as MainApp

    fun getMeetings(): List<MeetingModel> {
        return app.meetings.findAll()
    }
}