package org.ianus.revmeet.views.userlist

import android.content.Intent
import org.ianus.revmeet.main.MainApp
import org.ianus.revmeet.models.UserModel
//import org.ianus.revmeet.views.user.UserDetailView
import kotlin.jvm.java

class UserListPresenter(private val view: UserListView) {

    private val app = view.application as MainApp

    fun getUsers(): List<UserModel> {
        return app.users.findAll()
    }

    ///For later user when adding User details screen
   // fun doShowUser(user: UserModel) {
     //   val intent = Intent(view, UserDetailView::class.java)
    //    intent.putExtra("user", user)
     //   view.startActivity(intent)
   // }
}