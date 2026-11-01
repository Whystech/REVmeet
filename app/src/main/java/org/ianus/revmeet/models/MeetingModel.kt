package org.ianus.revmeet.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeetingModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                        var typeOfLocation : String = "",
                        var numberOfParticipants: Int = 1,
                        var maxNumberOfParticipants: Int = 1,
                          var lat : Double = 0.0,
                          var lng: Double = 0.0) : Parcelable
