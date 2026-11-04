package org.ianus.revmeet.views.user

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ianus.revmeet.R
import org.ianus.revmeet.databinding.ActivityUserBinding

class UserView : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ADDED: Inflate and display activity_user.xml using generated view binding.
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarUser)
        presenter = UserPresenter(this)
    }

    // ADDED: Show the form's Save and Cancel actions in the toolbar.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user, menu)
        return true
    }

    // Handle Save and Cancel menu selections.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_save_user -> {
                saveUser()
                true
            }
            R.id.item_cancel_user -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // Validate the required name, then ask the presenter to save the user.
    private fun saveUser() {
        val username = binding.userName.text.toString().trim()
        val email = binding.userEmail.text.toString().trim()

        if (username.isBlank()) {
            Snackbar.make(
                binding.root,
                R.string.enter_user_name,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        presenter.doAddUser(username, email)
    }
}