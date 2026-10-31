package org.ianus.revmeet.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class userModel (var id: Long = 0,
                          var username: String = "",
                          var email: String = "") : Parcelable
