package org.ianus.revmeet.views.user

import android.app.Activity
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel

class UserPresenter(private val view: UserView) {

    private val app = view.application as MainApp

    // Create the user in the app's in-memory store and close the form.
    fun doAddUser(username: String, email: String, displayName: String, bio: String) {
        app.users.create(
            UserModel(
                username = username,
                email = email,
                displayName = displayName,
                bio = bio
            )
        )

        view.setResult(Activity.RESULT_OK)
        view.finish()
    }
}