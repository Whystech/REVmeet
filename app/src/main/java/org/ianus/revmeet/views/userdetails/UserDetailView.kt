package org.ianus.revmeet.views.userdetails

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.ianus.revmeet.databinding.ActivityUserDetailBinding
import org.ianus.revmeet.models.UserModel

class UserDetailView : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    // Keep the Parcelable user passed from the list screen.
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Receive the same Parcelable model sent by UserListPresenter.
        val selectedUser =
            intent.extras?.getParcelable<UserModel>("user")

        if (selectedUser == null) {
            finish()
            return
        }

        user = selectedUser
        showUser()
    }
    // Display the selected user's values in the detail layout.
    private fun showUser() {
        binding.username.text = user.username
        binding.email.text = user.email
        binding.displayName.text = user.displayName
        binding.bio.text = user.bio
        binding.toolbarDetail.title = "${user.username} Details"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}