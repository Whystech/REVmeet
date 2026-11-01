package org.ianus.revmeet.views.meetinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.ianus.revmeet.databinding.ActivityMeetingListBinding
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import org.ianus.revmeet.R
import org.ianus.revmeet.views.meeting.MeetingView
import org.ianus.revmeet.models.MeetingModel

class MeetingListView : AppCompatActivity(), MeetingListener {

    private lateinit var binding: ActivityMeetingListBinding
    private lateinit var presenter: MeetingListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        presenter = MeetingListPresenter(this)
    }

    override fun onResume() {
        super.onResume()

        val meetings = presenter.getMeetings()
        binding.recyclerView.adapter = MeetingAdapter(meetings, this)

        val hasMeetings = meetings.isNotEmpty()
        binding.recyclerView.isVisible = hasMeetings
        binding.emptyState.isVisible = !hasMeetings
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_meeting_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_add_meeting -> {
                startActivity(Intent(this, MeetingView::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ADDED: Receive card taps and ask the presenter to open that meeting's details.
    override fun onMeetingClick(meeting: MeetingModel) {
        presenter.doShowMeeting(meeting)
    }
}