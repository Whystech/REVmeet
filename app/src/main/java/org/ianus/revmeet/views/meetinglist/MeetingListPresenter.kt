package org.ianus.revmeet.views.meetinglist

import android.content.Intent
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.MeetingModel
import org.ianus.revmeet.views.meeting.MeetingDetailView
import kotlin.jvm.java

class MeetingListPresenter(private val view: MeetingListView) {

    private val app = view.application as MainApp

    fun getMeetings(): List<MeetingModel> {
        return app.meetings.findAll()
    }

    fun doShowMeeting(meeting: MeetingModel) {
        val intent = Intent(view, MeetingDetailView::class.java)
        intent.putExtra("meeting", meeting)
        view.startActivity(intent)
    }
}