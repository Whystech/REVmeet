package org.ianus.revmeet.views.meeting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityMeetingBinding

class MeetingView : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingBinding
    private lateinit var presenter: MeetingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAdd)
        presenter = MeetingPresenter(this)
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
        val locationType = binding.typeOfLocation.text.toString().trim()
        val participants =
            binding.numberOfParticipants.text.toString().toIntOrNull()
        val maximum =
            binding.maxNumberOfParticipants.text.toString().toIntOrNull()

        if (title.isBlank()) {
            Snackbar.make(
                binding.root,
                R.string.enter_meetingPlace_title,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        if (participants == null || maximum == null ||
            participants < 0 || maximum < 1 || participants > maximum
        ) {
            Snackbar.make(
                binding.root,
                R.string.error_participant_counts,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        presenter.doAddMeeting(
            title = title,
            description = description,
            locationType = locationType,
            participants = participants,
            maximum = maximum
        )
    }
}