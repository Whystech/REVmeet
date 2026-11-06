package org.ianus.revmeet.views.participants

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityParticipantSelectionBinding
import org.ianus.revmeet.main.MainApp

class ListParticipantsView : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantSelectionBinding
    //Reusing remove participants presenter as it fits the purpose of providing added users.
    private lateinit var presenter: RemoveParticipantsPresenter
    private var meetingId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticipantSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        meetingId = intent.getLongExtra(ParticipantsSharedList.meetingId, -1L)
        if (meetingId < 0L) {
            finish()
            return
        }

        presenter = RemoveParticipantsPresenter(
            application as MainApp,
            meetingId
        )

        if (!presenter.meetingExists()) {
            finish()
            return
        }

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle(R.string.list_participants)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    /// Update the list when coming and going through views
    override fun onResume() {
        super.onResume()
        if (!::presenter.isInitialized) return
        showUsers()
    }

    private fun showUsers() {
        val users = presenter.getMeetingUsers()
        binding.emptyState.isVisible = users.isEmpty()
        binding.recyclerView.adapter = ListParticipantAdapter(users) { }

        if (users.isEmpty()) {
            binding.emptyState.setText(R.string.no_participants)
        }
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
