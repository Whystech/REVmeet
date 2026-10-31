package org.ianus.revmeet.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class carModel(var id: Long = 0,
                    var make: String = "",
                    var color: String = "",
                    var name: String = "" ) : Parcelable

