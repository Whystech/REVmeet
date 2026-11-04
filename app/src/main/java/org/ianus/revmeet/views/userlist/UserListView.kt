package org.ianus.revmeet.views.userlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.ianus.revmeet.databinding.ActivityUserListBinding
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import org.ianus.revmeet.R
import org.ianus.revmeet.views.user.UserView
import org.ianus.revmeet.models.UserModel
import org.ianus.revmeet.views.meetinglist.MeetingListView
import org.ianus.revmeet.views.userList.UserAdapter

class UserListView : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var presenter: UserListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        presenter = UserListPresenter(this)
    }

    override fun onResume() {
        super.onResume()

        val users = presenter.getUsers()
        binding.recyclerView.adapter = UserAdapter(users)

        val hasUsers = users.isNotEmpty()
        binding.recyclerView.isVisible = hasUsers
        binding.emptyState.isVisible = !hasUsers
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_add_user -> {
                startActivity(Intent(this, UserView::class.java))
                true
            }
            R.id.item_list_meeting -> {
                startActivity(Intent(this, MeetingListView::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}