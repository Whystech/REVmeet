package org.ianus.revmeet.views.meeting

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.ianus.revmeet.databinding.ActivityMeetingDetailBinding
import org.ianus.revmeet.models.MeetingModel

class MeetingDetailView : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingDetailBinding

    // Keep the Parcelable meeting passed from the list screen.
    private lateinit var meeting: MeetingModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Receive the same Parcelable model sent by MeetingListPresenter.
        val selectedMeeting =
            intent.extras?.getParcelable<MeetingModel>("meeting")

        if (selectedMeeting == null) {
            finish()
            return
        }

        meeting = selectedMeeting
        showMeeting()
    }

    // Display the selected meeting's values in the detail layout.
    private fun showMeeting() {
        binding.detailTitle.text = meeting.title
        binding.detailDescription.text = meeting.description
        binding.detailLocationType.text = meeting.typeOfLocation
        binding.detailParticipantCount.text =
            "${meeting.numberOfParticipants} / ${meeting.maxNumberOfParticipants}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}