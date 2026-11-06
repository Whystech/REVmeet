package org.ianus.revmeet.views.meeting

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.ianus.revmeet.databinding.ActivityMeetingDetailBinding
import org.ianus.revmeet.helper.CalendarConverter
import org.ianus.revmeet.models.MeetingModel
import android.content.Intent
import android.provider.Telephony
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.views.participants.AddParticipantsView
import org.ianus.revmeet.views.participants.ListParticipantsView
import org.ianus.revmeet.views.participants.ParticipantsSharedList
import org.ianus.revmeet.views.participants.RemoveParticipantsView

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

        binding.addParticipantsButton.setOnClickListener {
            openParticipantScreen(AddParticipantsView::class.java)
        }

        binding.removeParticipantsButton.setOnClickListener {
            openParticipantScreen(RemoveParticipantsView::class.java)
        }

        binding.listParticipantsButton.setOnClickListener {
            openParticipantScreen(ListParticipantsView::class.java)
        }

        showMeeting()
    }

    // Display the selected meeting's values in the detail layout.
    private fun showMeeting() {
        binding.detailTitle.text = meeting.title
        binding.detailDescription.text = meeting.description
        binding.detailLocationType.text = meeting.typeOfLocation
        binding.detailParticipantCount.text =
            "${meeting.numberOfParticipants} / ${meeting.maxNumberOfParticipants}"
        binding.startsAt.text = CalendarConverter.getDate(meeting.startsAt, "dd/MM/yyyy hh:mm")
    }
    /// Open participant view
    private fun openParticipantScreen(screen: Class<out AppCompatActivity>) {
        val intent = Intent(this, screen)
            .putExtra(ParticipantsSharedList.meetingId, meeting.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if (!::meeting.isInitialized) return
        val updatedMeeting =
            (application as MainApp).meetings.findById(meeting.id)
        if (updatedMeeting == null) {
            finish()
            return
        }

        meeting = updatedMeeting
        showMeeting()
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