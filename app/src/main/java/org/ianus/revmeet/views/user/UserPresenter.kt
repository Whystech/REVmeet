package org.ianus.revmeet.views.user

import android.app.Activity
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel

class UserPresenter(private val view: UserView) {

    private val app = view.application as MainApp
    private val userToEdit: UserModel? =
        view.intent.getParcelableExtra("user_edit")

    val isEditing: Boolean
        get() = userToEdit != null

    init {
        userToEdit?.let(view::showUser)
    }

    fun doAddUser(
        username: String,
        email: String,
        displayName: String,
        bio: String
    ) {
        val existingUser = userToEdit

        if (existingUser == null) {
            app.users.create(
                UserModel(
                    username = username,
                    email = email,
                    displayName = displayName,
                    bio = bio
                )
            )
        } else {
            existingUser.username = username
            existingUser.email = email
            existingUser.displayName = displayName
            existingUser.bio = bio
            app.users.update(existingUser)
        }

        view.setResult(Activity.RESULT_OK)
        view.finish()
    }

    fun doDeleteUser() {
    val user = userToEdit ?: return
    app.users.delete(user)
    view.setResult(Activity.RESULT_OK)
    view.finish()
}
}