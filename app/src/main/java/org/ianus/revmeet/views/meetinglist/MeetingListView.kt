package org.ianus.revmeet.views.meetinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.ianus.revmeet.databinding.ActivityMeetingListBinding

class MeetingListView : AppCompatActivity() {

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
        binding.recyclerView.adapter = MeetingAdapter(meetings)

        val hasMeetings = meetings.isNotEmpty()
        binding.recyclerView.isVisible = hasMeetings
        binding.emptyState.isVisible = !hasMeetings
    }
}