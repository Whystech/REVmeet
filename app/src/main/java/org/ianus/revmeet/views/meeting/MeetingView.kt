package org.ianus.revmeet.views.meeting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityMeetingBinding
import java.util.Calendar


class MeetingView : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingBinding
    private lateinit var presenter: MeetingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAdd)
        presenter = MeetingPresenter(this)

        /// Set values for participants to be used in Number Picker for number of participants and max number of participants
        binding.numberOfParticipants.minValue = 1
        binding.numberOfParticipants.maxValue = 100
        binding.numberOfParticipants.value = 1
        binding.numberOfParticipants.wrapSelectorWheel = false
        ///Max Participants bindings
        binding.maxNumberOfParticipants.minValue = 1
        binding.maxNumberOfParticipants.maxValue = 100
        binding.maxNumberOfParticipants.value = 8
        binding.maxNumberOfParticipants.wrapSelectorWheel = false
        binding.meetingTime.setIs24HourView(true)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_meeting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_save_meeting -> {
                saveMeeting()
                true
            }
            R.id.item_cancel_meeting -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveMeeting() {
        val title = binding.meetingTitle.text.toString().trim()
        val description = binding.meetingDescription.text.toString().trim()
        val locationType = binding.typeOfLocation.selectedItem.toString()
        val participants =
            binding.numberOfParticipants.value
        val maximum =
            binding.maxNumberOfParticipants.value

        if (title.isBlank()) {
            Snackbar.make(
                binding.root,
                R.string.enter_meetingPlace_title,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }
        /// check if participants is higher than max participants
        if (participants > maximum) {
            Snackbar.make(
                binding.root,
                R.string.error_participant_counts,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        //Calendar implementation
        val startsAtCalendar = Calendar.getInstance().apply {
            set(
                binding.meetingDate.year,
                binding.meetingDate.month,
                binding.meetingDate.dayOfMonth,
                binding.meetingTime.hour,
                binding.meetingTime.minute,
                0
            )
            set(Calendar.MILLISECOND, 0)
        }
        val startsAt = startsAtCalendar.timeInMillis


        presenter.doAddMeeting(
            title = title,
            description = description,
            locationType = locationType,
            participants = participants,
            maximum = maximum,
            startsAt = startsAt
        )
    }
}