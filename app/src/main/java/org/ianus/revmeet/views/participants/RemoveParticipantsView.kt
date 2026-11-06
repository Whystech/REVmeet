package org.ianus.revmeet.views.participants

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityParticipantSelectionBinding
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel

class RemoveParticipantsView : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantSelectionBinding
    private lateinit var presenter: RemoveParticipantsPresenter
    private var meetingId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticipantSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

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
        binding.toolbar.setTitle(R.string.remove_participants)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_addparticipant, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_cancel_user -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /// Update the list when coming and going through views
    override fun onResume() {
        super.onResume()
        if (!::presenter.isInitialized) return
        showUsers()
    }

    private fun showUsers() {
        val users = presenter.getMeetingUsers()
        binding.recyclerView.adapter = ParticipantAdapter(users, ::onUserSelected)
        binding.emptyState.isVisible = users.isEmpty()

        if (users.isEmpty()) {
            binding.emptyState.setText(R.string.no_participants_to_remove)
        }
    }

    private fun onUserSelected(user: UserModel) {
        if (presenter.removeUser(user.id)) {
            showUsers()
        } else {
            Snackbar.make(
                binding.root,
                R.string.could_not_remove_participant,
                Snackbar.LENGTH_LONG
            ).show()
            showUsers()
        }
    }
}