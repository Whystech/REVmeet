package org.ianus.revmeet.views.meeting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityMeetingBinding
import org.ianus.revmeet.models.MeetingModel
import java.util.Calendar


class MeetingView : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingBinding
    private lateinit var presenter: MeetingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAdd)

        /// Set values for participants to be used in Number Picker for max number of participants
        binding.maxNumberOfParticipants.minValue = 1
        binding.maxNumberOfParticipants.maxValue = 100
        binding.maxNumberOfParticipants.value = 8
        binding.maxNumberOfParticipants.wrapSelectorWheel = false
        binding.meetingTime.setIs24HourView(true)
        presenter = MeetingPresenter(this)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_meeting, menu)
        menu.findItem(R.id.item_delete_meeting).isVisible = presenter.isEditing
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

            R.id.item_delete_meeting -> {
                presenter.doDeleteMeeting()
                true
}
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveMeeting() {
        val title = binding.meetingTitle.text.toString().trim()
        val description = binding.meetingDescription.text.toString().trim()
        val locationType = binding.typeOfLocation.selectedItem.toString()
        val maximum = binding.maxNumberOfParticipants.value

        if (title.isBlank()) {
            Snackbar.make(
                binding.root,
                R.string.enter_meetingPlace_title,
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


        presenter.doSaveMeeting(
            title = title,
            description = description,
            locationType = locationType,
            maximum = maximum,
            startsAt = startsAt
        )
/// Change toolbar name after starting the presenter
        binding.toolbarAdd.setTitle(
            if (presenter.isEditing) R.string.edit_meeting_title
            else R.string.button_addMeetingPlace
        )
    }

    fun showMeeting(meeting: MeetingModel) {
        binding.toolbarAdd.title = getString(R.string.edit_meeting_title)
        binding.meetingTitle.setText(meeting.title)
        binding.meetingDescription.setText(meeting.description)

        val locationIndex = (0 until binding.typeOfLocation.count)
            .firstOrNull {
                binding.typeOfLocation.getItemAtPosition(it).toString() ==
                        meeting.typeOfLocation
            }
        if (locationIndex != null) {
            binding.typeOfLocation.setSelection(locationIndex)
        }

        binding.maxNumberOfParticipants.value = meeting.maxNumberOfParticipants

        val calendar = Calendar.getInstance().apply {
            timeInMillis = meeting.startsAt
        }
        binding.meetingDate.updateDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        binding.meetingTime.hour = calendar.get(Calendar.HOUR_OF_DAY)
        binding.meetingTime.minute = calendar.get(Calendar.MINUTE)
    }
}