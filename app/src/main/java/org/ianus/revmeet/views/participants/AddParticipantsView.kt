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

class AddParticipantsView : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantSelectionBinding
    private lateinit var presenter: AddParticipantsPresenter
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

        presenter = AddParticipantsPresenter(
            application as MainApp,
            meetingId
        )

        if (!presenter.meetingExists()) {
            finish()
            return
        }

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle(R.string.add_participants)
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
    override fun onResume() {
        super.onResume()
        if (!::presenter.isInitialized) return
        showUsers()
    }

    private fun showUsers() {
        val users = presenter.getAvailableUsers()
        binding.recyclerView.adapter = ParticipantAdapter(users, ::onUserSelected)

        binding.emptyState.isVisible = users.isEmpty()
        if (users.isEmpty()) {
            binding.emptyState.setText(
                if (presenter.isAtCapacity()) {
                    R.string.meeting_at_capacity
                } else {
                    R.string.no_users_to_add
                }
            )
        }
    }

    private fun onUserSelected(user: UserModel) {
        if (presenter.addUser(user.id)) {
            showUsers()
        } else {
            Snackbar.make(
                binding.root,
                R.string.could_not_add_participant,
                Snackbar.LENGTH_LONG
            ).show()
            showUsers()
        }
    }
}