package org.ianus.revmeet.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.ianus.revmeet.helper.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

private const val USER_JSON_FILE = "users.json"
private val userGsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
private val userListType: Type =
    object : TypeToken<ArrayList<UserModel>>() {}.type

private fun generateUserRandomId(): Long {
    return Random().nextLong()
}

class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, USER_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }

    override fun findById(id: Long): UserModel? {
        return users.find { it.id == id }
    }

    override fun create(user: UserModel) {
        user.id = generateUserRandomId()
        users.add(user)
        serialize()
    }

    override fun update(user: UserModel) {
        val foundUser = users.find { it.id == user.id }

        if (foundUser != null) {
            foundUser.username = user.username
            foundUser.email = user.email
            foundUser.displayName = user.displayName
            foundUser.bio = user.bio
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = userGsonBuilder.toJson(users, userListType)
        write(context, USER_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, USER_JSON_FILE)
        users = userGsonBuilder.fromJson(jsonString, userListType)
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}