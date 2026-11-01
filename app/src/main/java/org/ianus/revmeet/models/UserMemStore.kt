package org.ianus.revmeet.models

import org.ianus.revmeet.models.UserModel
import org.ianus.revmeet.models.UserStore
import timber.log.Timber.i

var lastUserId = 0L

internal fun getUserId(): Long {
    return lastUserId++
}

class UserMemStore : UserStore {
    val users = ArrayList<UserModel>()

    override fun findAll(): List<UserModel> {
        return users
    }

    override fun findById(id:Long) : UserModel? {
        val foundUser: UserModel? = users.find { it.id == id }
        return foundUser
    }

    override fun create(user: UserModel) {
        user.id = getUserId()
        users.add(user)
        logAll()
    }

    ///added additional update possibilities as the ing has extended properties
    override fun update(user: UserModel) {
        val foundUser: UserModel? = users.find { p -> p.id == user.id }
        if (foundUser != null) {
            foundUser.username = user.username
            foundUser.email = user.email
            logAll()
        }
    }
    private fun logAll() {
        users.forEach { i("$it") }
    }

    override fun delete(user: UserModel) {
        users.remove(user)
    }
}